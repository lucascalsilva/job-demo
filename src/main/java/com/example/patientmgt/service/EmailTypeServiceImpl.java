package com.example.patientmgt.service;

import com.example.patientmgt.model.EmailTypeEntity;
import com.example.patientmgt.repository.EmailTypeRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailTypeServiceImpl implements PatientEmailService {

    private final EmailTypeRepository emailTypeRepository;

    @Override
    public EmailTypeEntity create(@NonNull EmailTypeEntity emailType) {
        return emailTypeRepository.save(emailType);
    }

    @Override
    public List<EmailTypeEntity> createAll(List<EmailTypeEntity> emailTypes) {
        return emailTypes.stream().map(this::create).collect(Collectors.toList());
    }

    @Override
    public List<EmailTypeEntity> findAll() {
        return emailTypeRepository.findAll();
    }
}
