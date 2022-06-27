package com.vinsguru.reactivemongo;

import com.vinsguru.reactivemongo.repository.FreelancerRepository;
import com.vinsguru.reactivemongo.service.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.time.Duration;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveMongoApplication implements CommandLineRunner {

	@Autowired
	private FreelancerRepository repository;

	@Autowired
	private FreelancerService service;

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// approach - 1
		this.repository
				.getSkilledPeople()
				.doOnNext(System.out::println)
				.blockLast(Duration.ofSeconds(10));

		// approach - 2
		this.service
				.getSkilledPeople()
				.doOnNext(System.out::println)
				.blockLast(Duration.ofSeconds(10));
		
	}
}
