package dev.rabbit.fila.user.repository;
import dev.rabbit.fila.user.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByUserEmail(String userEmail);

    Optional<UserModel> findByUserEmail(String userEmail);

}
