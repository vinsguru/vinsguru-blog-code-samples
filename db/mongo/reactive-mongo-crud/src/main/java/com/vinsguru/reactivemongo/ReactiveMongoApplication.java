package com.vinsguru.reactivemongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoApplication.class, args);
	}

}
