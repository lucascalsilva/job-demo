package com.example.patientmgt.repository;

import com.example.patientmgt.model.EmailTypeEntity;
import com.example.patientmgt.model.PatientPreferenceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTypeRepository extends MongoRepository<EmailTypeEntity, String> {

}
