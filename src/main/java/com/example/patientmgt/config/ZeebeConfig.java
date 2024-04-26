package com.example.patientmgt.config;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.ZeebeClientBuilder;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class ZeebeConfig {

    private static final String zeebeGrpc = "http://localhost:26500";
    private static final String zeebeRest = "http://localhost:26500";
    private static final String audience = "zeebe-api";
    private static final String clientId = "zeebe";
    private static final String clientSecret = "zecret";
    private static final String oAuthAPI = "http://localhost:18080/auth/realms/camunda-platform/protocol/openid-connect/token";

    @Bean
    public ZeebeClient zeebeClient(){
        OAuthCredentialsProvider credentialsProvider =
                new OAuthCredentialsProviderBuilder()
                        .authorizationServerUrl(oAuthAPI)
                        .audience(audience)
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .build();

        return ZeebeClient.newClientBuilder()
                .grpcAddress(URI.create(zeebeGrpc))
                .restAddress(URI.create(zeebeRest))
                .credentialsProvider(credentialsProvider)
                .usePlaintext().build();
    }
}
