package com.example.patientmgt.controller;

import com.example.patientmgt.model.EmailTypeEntity;
import com.example.patientmgt.service.PatientEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email-types")
@RequiredArgsConstructor
public class EmailTypeController {

    private final PatientEmailService emailTypeService;

    @GetMapping
    public ResponseEntity<List<EmailTypeEntity>> getAll(){
        return ResponseEntity.ok(emailTypeService.findAll());
    }
}
