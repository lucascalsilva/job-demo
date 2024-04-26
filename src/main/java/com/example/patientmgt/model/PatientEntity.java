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
public class PatientEntity {

    private String patientId = UUID.randomUUID().toString();
    private String patientDocumentId;
    private PatientProfiles patientProfile;
    private AccessFrequencies accessFrequency;

    private enum PatientProfiles {
        BRONZE, SILVER, GOLD;
    }

    private enum AccessFrequencies {
        LOW, MEDIUM, HIGH
    }

}
