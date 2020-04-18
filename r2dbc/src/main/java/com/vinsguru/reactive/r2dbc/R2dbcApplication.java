package com.vinsguru.reactive.r2dbc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@SpringBootApplication
@EnableR2dbcRepositories
public class R2dbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(R2dbcApplication.class, args);
	}

}
