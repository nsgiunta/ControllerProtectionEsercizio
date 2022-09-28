package co.develhope.ControllerProtectionEsercizio.auth.service;

import co.develhope.ControllerProtectionEsercizio.auth.entities.SignupActivationDTO;
import co.develhope.ControllerProtectionEsercizio.auth.entities.SignupDTO;
import co.develhope.ControllerProtectionEsercizio.notification.MailNotificationService;
import co.develhope.ControllerProtectionEsercizio.user.entities.User;
import co.develhope.ControllerProtectionEsercizio.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailNotificationService mailNotificationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signup(SignupDTO signupDTO) throws Exception{
        User userInDB = userRepository.findByEmail(signupDTO.getEmail());
        if(userInDB != null) throw new Exception("User already exists");
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setSurname(signupDTO.getSurname());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());
        mailNotificationService.sendActivationEmail(user);
        return userRepository.save(user);
    }

    public User activate(SignupActivationDTO signupActivationDTO) throws Exception{
        User user = userRepository.findByActivationCode(signupActivationDTO.getActivationCode());
        if(user == null) throw new Exception("user not found");
        user.setActive(true);
        user.setActivationCode(null);
        return userRepository.save(user);
    }
}
