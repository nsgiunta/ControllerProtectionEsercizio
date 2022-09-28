package co.develhope.ControllerProtectionEsercizio.auth.controller;

import co.develhope.ControllerProtectionEsercizio.auth.entities.LoginDTO;
import co.develhope.ControllerProtectionEsercizio.auth.entities.LoginRTO;
import co.develhope.ControllerProtectionEsercizio.auth.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public LoginRTO login(@RequestBody LoginDTO loginDTO) throws Exception{
        LoginRTO loginRto = loginService.login(loginDTO);
        if (loginRto == null) throw new RuntimeException("Cannot login");
        return loginRto;
    }
}
