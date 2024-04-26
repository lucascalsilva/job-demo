package com.example.patientmgt.repository;

import com.example.patientmgt.model.PatientEntity;
import com.example.patientmgt.model.PatientPreferenceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<PatientEntity, String> {

    PatientEntity findPatientEntityByPatientDocumentId(String patientId);

}
