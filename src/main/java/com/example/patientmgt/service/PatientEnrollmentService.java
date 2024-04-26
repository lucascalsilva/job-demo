package com.example.patientmgt.service;

import com.example.patientmgt.model.PatientPreferenceEntity;

import java.util.List;

public interface PatientEnrollmentService {

    PatientPreferenceEntity create(PatientPreferenceEntity patientPreference);
    void remove(String patientPreferenceId);
    List<PatientPreferenceEntity> findAll();
}
