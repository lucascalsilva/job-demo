package com.example.patientmgt.service;

import com.example.patientmgt.dto.PatientEmailDTO;
import com.example.patientmgt.model.EmailTypeEntity;

public interface EmailService {

    void sendEmail(PatientEmailDTO email);
}
