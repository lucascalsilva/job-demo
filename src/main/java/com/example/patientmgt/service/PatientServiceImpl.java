package com.example.patientmgt.service;

import com.example.patientmgt.model.PatientEntity;
import com.example.patientmgt.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public List<PatientEntity> findAll() {
        return patientRepository.findAll();
    }

    public PatientEntity findByDocumentId(String documentId){
        return patientRepository.findPatientEntityByPatientDocumentId(documentId);
    }

    @Override
    public List<PatientEntity> createAll(List<PatientEntity> patients) {
        return patients.stream().map(this::create).collect(Collectors.toList());
    }

    @Override
    public PatientEntity create(PatientEntity patientEntity) {
        return patientRepository.save(patientEntity);
    }
}
