package co.develhope.ControllerProtectionEsercizio.salary.controller;

import co.develhope.ControllerProtectionEsercizio.salary.entities.CreateSalaryDTO;
import co.develhope.ControllerProtectionEsercizio.salary.entities.Salary;
import co.develhope.ControllerProtectionEsercizio.salary.repository.SalaryRepository;
import co.develhope.ControllerProtectionEsercizio.user.entities.User;
import co.develhope.ControllerProtectionEsercizio.user.repository.UserRepository;
import co.develhope.ControllerProtectionEsercizio.user.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public List<Salary> getAll(){
        return salaryRepository.findAll();
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public Salary getSalaryById(@PathVariable Long id){
        return salaryRepository.findByUserId(id);
    }

    @PostMapping("/user/{id}")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public Salary createSalary(@PathVariable Long id, @RequestBody CreateSalaryDTO salaryDTO){
        Salary s = new Salary();
        s.setAmount(salaryDTO.getAmount());
        Optional<User> user = userRepository.findById(id);
        s.setUser(user.get());
        return salaryRepository.save(s);
    }
}
