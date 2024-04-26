package com.example.patientmgt.service;

import com.example.patientmgt.dto.PatientEmailDTO;
import com.example.patientmgt.model.EmailTypeEntity;
import com.example.patientmgt.model.PatientPreferenceEntity;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class PatientEmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    @Override
    @SneakyThrows
    public void sendEmail(PatientEmailDTO patientEmail) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(patientEmail.getPatientPreference().getPatientEmail());
        mimeMessageHelper.setSubject(patientEmail.getEmailType().getDescription());

        Context context = new Context();

        context.setVariable("patientName", patientEmail.getPatientPreference().getPatientName());
        String processedString = templateEngine.process(patientEmail.getEmailType().getEmailType(), context);

        mimeMessageHelper.setText(processedString, true);

        emailSender.send(mimeMessage);
    }
}
