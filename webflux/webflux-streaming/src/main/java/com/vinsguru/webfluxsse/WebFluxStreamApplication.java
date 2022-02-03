package com.vinsguru.webfluxsse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebFluxStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxStreamApplication.class, args);
	}

}
