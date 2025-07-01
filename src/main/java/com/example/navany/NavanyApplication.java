package com.example.navany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.navany")
@EnableJpaRepositories(basePackages = "com.example.navany.repository")
@EntityScan(basePackages = "com.example.navany.entities")
public class NavanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NavanyApplication.class, args);
	}

}
