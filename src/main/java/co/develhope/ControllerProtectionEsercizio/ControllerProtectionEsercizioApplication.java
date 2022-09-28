package co.develhope.ControllerProtectionEsercizio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
public class ControllerProtectionEsercizioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControllerProtectionEsercizioApplication.class, args);
	}

}
