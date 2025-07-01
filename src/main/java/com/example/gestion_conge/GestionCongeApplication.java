package com.example.gestion_conge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.navany")
@EnableJpaRepositories(basePackages = "com.example.navany.repository")
@EntityScan(basePackages = "com.example.navany.entities")
public class GestionCongeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCongeApplication.class, args);
	}

}
