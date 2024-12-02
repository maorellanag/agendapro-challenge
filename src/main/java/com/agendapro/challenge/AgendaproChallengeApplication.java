package com.agendapro.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AgendaproChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaproChallengeApplication.class, args);
	}

}
