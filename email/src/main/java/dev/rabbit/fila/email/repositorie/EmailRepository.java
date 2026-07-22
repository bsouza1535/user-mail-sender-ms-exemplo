package dev.rabbit.fila.email.repositorie;
import dev.rabbit.fila.email.domain.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
