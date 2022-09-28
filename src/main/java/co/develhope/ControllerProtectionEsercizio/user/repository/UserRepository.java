package co.develhope.ControllerProtectionEsercizio.user.repository;

import co.develhope.ControllerProtectionEsercizio.auth.entities.RestorePasswordDTO;
import co.develhope.ControllerProtectionEsercizio.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByPasswordResetCode(RestorePasswordDTO restorePasswordDTO);

    User findByActivationCode(String activationCode);
}
