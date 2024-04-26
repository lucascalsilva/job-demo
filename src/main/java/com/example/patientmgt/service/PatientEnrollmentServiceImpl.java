package com.example.patientmgt.service;

import com.example.patientmgt.model.PatientPreferenceEntity;
import com.example.patientmgt.repository.PatientPreferenceRepository;
import com.example.patientmgt.repository.PatientRepository;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PatientEnrollmentServiceImpl implements PatientEnrollmentService {

    private final PatientPreferenceRepository patientPreferenceRepository;
    private final PatientService patientService;
    private final ZeebeClient zeebeClient;

    public PatientPreferenceEntity create(@NonNull PatientPreferenceEntity patientPreference){
        patientPreference.setPatientPreferenceId(String.valueOf(UUID.randomUUID()));

        PatientPreferenceEntity savedEntity = patientPreferenceRepository.save(patientPreference);
        savedEntity.setPatient(patientService.findByDocumentId(savedEntity.getPatientDocumentId()));

        Map<String, Object> processVariables = new HashMap<String, Object>();
        processVariables.put("patientPreference", savedEntity);

        zeebeClient.newPublishMessageCommand()
                .messageName("PatientEnrollmentReceivedMessage")
                .withoutCorrelationKey()
                .variables(processVariables).requestTimeout(Duration.ofMinutes(1)).send()
                .join(60, TimeUnit.SECONDS);

        return savedEntity;
    }

    @Override
    public void remove(String patientPreferenceId) {
        patientPreferenceRepository
                .deletePatientPreferenceEntityByPatientPreferenceId(patientPreferenceId);

        zeebeClient.newPublishMessageCommand().messageName("CancelEnrollmentsMessage")
                .correlationKey(patientPreferenceId)
                .requestTimeout(Duration.ofMinutes(1)).send()
                .join(60, TimeUnit.SECONDS);
    }

    public List<PatientPreferenceEntity> findAll(){
        return patientPreferenceRepository.findAll();
    }

}
