package com.example.patientmgt.service;

import com.example.patientmgt.model.PatientEntity;

import java.util.List;

public interface PatientService {

    List<PatientEntity> findAll();
    List<PatientEntity> createAll(List<PatientEntity> patients);
    PatientEntity findByDocumentId(String documentId);
    PatientEntity create(PatientEntity patientEntity);
}
