package com.example.patientmgt.service;

import com.example.patientmgt.model.EmailTypeEntity;

import java.util.List;

public interface PatientEmailService {

    EmailTypeEntity create(EmailTypeEntity emailType);
    List<EmailTypeEntity> createAll(List<EmailTypeEntity> emailTypes);
    List<EmailTypeEntity> findAll();
}
