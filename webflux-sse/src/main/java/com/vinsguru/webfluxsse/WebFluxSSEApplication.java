package com.vinsguru.webfluxsse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebFluxSSEApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxSSEApplication.class, args);
	}

}
