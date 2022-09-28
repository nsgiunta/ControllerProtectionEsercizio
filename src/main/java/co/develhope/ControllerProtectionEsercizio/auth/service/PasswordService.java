package co.develhope.ControllerProtectionEsercizio.auth.service;

import co.develhope.ControllerProtectionEsercizio.auth.entities.RequestPasswordDTO;
import co.develhope.ControllerProtectionEsercizio.auth.entities.RestorePasswordDTO;
import co.develhope.ControllerProtectionEsercizio.notification.MailNotificationService;
import co.develhope.ControllerProtectionEsercizio.user.entities.User;
import co.develhope.ControllerProtectionEsercizio.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailNotificationService mailNotificationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User request(RequestPasswordDTO requestPasswordDTO) throws Exception{
        User userFromDB = userRepository.findByEmail(requestPasswordDTO.getEmail());
        if(userFromDB == null) throw new Exception("Cannot find user");
        userFromDB.setPasswordResetCode(UUID.randomUUID().toString());
        mailNotificationService.sendPasswordResetMail(userFromDB);
        return userRepository.save(userFromDB);
    }

    public User restore(RestorePasswordDTO restorePasswordDTO) throws Exception{
        User userFromDB = userRepository.findByPasswordResetCode(restorePasswordDTO);
        if(userFromDB == null) throw new Exception("Cannot find user");
        userFromDB.setPassword(passwordEncoder.encode(restorePasswordDTO.getNewPassword()));
        userFromDB.setPasswordResetCode(null);

        userFromDB.setActive(true);
        userFromDB.setActivationCode(null);

        return userRepository.save(userFromDB);
    }
}
