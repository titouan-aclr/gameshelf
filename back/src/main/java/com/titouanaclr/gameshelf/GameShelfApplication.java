package com.titouanaclr.gameshelf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GameShelfApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GameShelfApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("COUCOU2");
	}
}
