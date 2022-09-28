package co.develhope.ControllerProtectionEsercizio.salary.repository;

import co.develhope.ControllerProtectionEsercizio.salary.entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    Salary findByUserId(Long id);
}
