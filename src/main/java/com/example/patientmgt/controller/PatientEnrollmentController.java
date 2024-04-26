package com.example.patientmgt.controller;

import com.example.patientmgt.model.PatientPreferenceEntity;
import com.example.patientmgt.service.PatientEnrollmentService;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/patient-enrollments")
@RequiredArgsConstructor
public class PatientEnrollmentController {

    private final PatientEnrollmentService patientEnrollmentService;
    private final ZeebeClient zeebeClient;

    @PostMapping
    public ResponseEntity<PatientPreferenceEntity> create(@NonNull @RequestBody PatientPreferenceEntity patientPreference){
        return ResponseEntity.ok(patientEnrollmentService.create(patientPreference));
    }

    @DeleteMapping("/{patientPreferenceId}")
    public void remove(@NonNull @PathVariable String patientPreferenceId){
        patientEnrollmentService.remove(patientPreferenceId);
    }

    @GetMapping
    public ResponseEntity<List<PatientPreferenceEntity>> getAll(){
        return ResponseEntity.ok(patientEnrollmentService.findAll());
    }
}
