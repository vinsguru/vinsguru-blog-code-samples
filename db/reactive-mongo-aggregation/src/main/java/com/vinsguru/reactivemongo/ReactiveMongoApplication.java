package com.vinsguru.reactivemongo;

import com.vinsguru.reactivemongo.repository.FreelancerRepository;
import com.vinsguru.reactivemongo.service.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

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
				.subscribe(System.out::println);

		// approach - 2
		this.service
				.getSkilledPeople()
				.subscribe(System.out::println);

	}
}
