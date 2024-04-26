package com.example.patientmgt.jobhandler;

import com.example.patientmgt.dto.PatientEmailDTO;
import com.example.patientmgt.model.EmailTypeEntity;
import com.example.patientmgt.model.PatientPreferenceEntity;
import com.example.patientmgt.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
public class EmailJobHandler implements JobHandler {

    private final EmailService patientEmailService;
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void handle(JobClient client, ActivatedJob job) {
        var patientPreferenceObj = job.getVariable("patientPreference");
        PatientPreferenceEntity patientPreferenceEntity = objectMapper.convertValue(patientPreferenceObj, PatientPreferenceEntity.class);

        var emailTypeObj = job.getVariable("emailType");
        EmailTypeEntity emailTypeEntity = objectMapper.convertValue(emailTypeObj, EmailTypeEntity.class);

        PatientEmailDTO patientEmail = PatientEmailDTO.builder()
                .patientPreference(patientPreferenceEntity)
                        .emailType(emailTypeEntity).build();

        log.info("Sending email to {}", patientPreferenceEntity.getPatientEmail());
        patientEmailService.sendEmail(patientEmail);

        client.newCompleteCommand(job.getKey()).send().join(60, TimeUnit.SECONDS);
    }
}
