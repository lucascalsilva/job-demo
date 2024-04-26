package com.example.patientmgt.startup;

import com.example.patientmgt.model.EmailTypeEntity;
import com.example.patientmgt.model.PatientEntity;
import com.example.patientmgt.service.PatientEmailService;
import com.example.patientmgt.service.PatientService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class LoadDataBootstrap implements CommandLineRunner {

    private final PatientEmailService emailTypeService;
    private final PatientService patientService;
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void run(String... args) throws Exception {
        loadData(emailTypeService.findAll().isEmpty(), "emailTypes.json",
                new TypeReference<List<EmailTypeEntity>>() {
                },
                emailTypeService::createAll);

        loadData(patientService.findAll().isEmpty(), "patients.json",
                new TypeReference<List<PatientEntity>>() {
                },
                patientService::createAll);
    }

    @SneakyThrows
    public <T> void loadData(Boolean load, String file, TypeReference<T> typeReference, Function<T, T> fn) {
        if (load) {
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
            T t = objectMapper.readValue(resourceAsStream, typeReference);
            fn.apply(t);
        }
    }
}
