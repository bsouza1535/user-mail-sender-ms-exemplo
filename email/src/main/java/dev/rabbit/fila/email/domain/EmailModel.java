package dev.rabbit.fila.email.domain;

import dev.rabbit.fila.email.enums.EmailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_EMAIL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailModel {

    @Transient
    private final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailId;
    private UUID userId;
    private String emailFrom;
    private String emailTo;
    private String emailSubject;
    @Column(columnDefinition = "BODY")
    private String body;
    private LocalDateTime sendDateEmail;
    private EmailStatus statusEmail;
}
