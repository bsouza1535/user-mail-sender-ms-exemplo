package dev.rabbit.fila.email.service;
import dev.rabbit.fila.email.domain.EmailModel;
import dev.rabbit.fila.email.enums.EmailStatus;
import dev.rabbit.fila.email.repositorie.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Value("bruno.souza.unibratec.edu.br@gmail.com")
    private String emailFrom;

    @Transactional
    public void sendEmail(EmailModel emailModel) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getEmailSubject());
            message.setText(emailModel.getBody());
            mailSender.send(message);
            emailModel.setStatusEmail(EmailStatus.SENT);
            emailModel.setSendDateEmail(LocalDateTime.now());
        } catch (Exception e) {
            emailModel.setStatusEmail(EmailStatus.FAILED);
            System.out.println("Erro ao enviar email: " + e.getMessage());
        }

        emailRepository.save(emailModel);
    }

}
