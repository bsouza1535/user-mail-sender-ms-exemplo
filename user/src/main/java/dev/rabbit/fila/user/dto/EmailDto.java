package dev.rabbit.fila.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class EmailDto {
    private UUID userId;
    private String emailTo;
    private String emailSubject;
    private String body;
}
