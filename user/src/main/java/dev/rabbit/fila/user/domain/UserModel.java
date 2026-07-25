package dev.rabbit.fila.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "userId")
@Table(name = "usersdb")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", updatable = false, nullable = false, unique = true)
    private UUID userId;

    @NotBlank
    @Size(max = 200)
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotBlank
    @Email
    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @NotBlank
    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "user_phone_number")
    private String userPhoneNumber;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_city")
    private String userCity;

    @Column(name = "user_state")
    private String userState;

    @Column(name = "user_zip")
    private String userZip;

    @Column(name = "user_country")
    private String userCountry;

}
