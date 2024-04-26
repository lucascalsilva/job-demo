package com.example.patientmgt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientPreferenceEntity {

    private String patientPreferenceId = UUID.randomUUID().toString();
    private String patientDocumentId;
    private String patientName;
    private String patientEmail;
    private PatientEntity patient;
    private List<EmailTypeEntity> emailTypeList;
}
