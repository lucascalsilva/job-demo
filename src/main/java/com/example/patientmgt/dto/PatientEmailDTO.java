package com.example.patientmgt.dto;

import com.example.patientmgt.model.EmailTypeEntity;
import com.example.patientmgt.model.PatientPreferenceEntity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientEmailDTO {

    private PatientPreferenceEntity patientPreference;
    private EmailTypeEntity emailType;

}
