package com.fintech.masoori;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MasooriApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasooriApplication.class, args);
	}

}
