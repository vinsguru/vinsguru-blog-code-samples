package com.vinsguru.webclientfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients
public class WebClientFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebClientFeignApplication.class, args);
	}

}
