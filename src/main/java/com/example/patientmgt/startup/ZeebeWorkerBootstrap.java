package com.example.patientmgt.startup;

import com.example.patientmgt.jobhandler.EmailJobHandler;
import com.example.patientmgt.service.EmailService;
import com.example.patientmgt.utils.AppUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.ZeebeClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.example.patientmgt.utils.AppUtils.*;

@Component
@RequiredArgsConstructor
public class ZeebeWorkerBootstrap implements CommandLineRunner {

    private final EmailService emailService;
    private final ZeebeClient zeebeClient;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        try(var jobWorkerBuilder = zeebeClient.newWorker()
                .jobType("send-email")
                .handler(new EmailJobHandler(emailService, objectMapper)).open()){
            waitUntilSystemInput("exit");
        }
    }
}
