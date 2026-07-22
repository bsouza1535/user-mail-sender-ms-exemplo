package dev.rabbit.fila.email.consumer;
import dev.rabbit.fila.email.domain.EmailModel;
import dev.rabbit.fila.email.dto.EmailDto;
import dev.rabbit.fila.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "email-queue")

    public void listenEmailQueue(@Payload EmailDto emailDto) {
       var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
    }
}
