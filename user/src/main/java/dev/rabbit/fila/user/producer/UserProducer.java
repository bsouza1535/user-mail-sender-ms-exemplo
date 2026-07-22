package dev.rabbit.fila.user.producer;
import dev.rabbit.fila.user.domain.UserModel;
import dev.rabbit.fila.user.dto.EmailDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

   final RabbitTemplate rabbitTemplate;

    @Value("${EMAIL_USERNAME}")
    private String routingKey;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishEvent(UserModel userModel) {
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setEmailSubject("Welcome to devFilaExemplo");
        emailDto.setBody("Hello " + userModel.getName() + ",\n\nWelcome to devFilaExemplo! We are excited to have you on board.\n\nBest regards,\ndevFilaExemplo Team");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}
