package com.vinsguru.reactivemongo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinsguru.reactivemongo.entity.Movie;
import com.vinsguru.reactivemongo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveMoviesApplication implements CommandLineRunner {

	@Autowired
	private MovieRepository repository;

	@Value("classpath:movies.json")
	private Resource resource;

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMoviesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Movie> movieList = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
		Flux.fromIterable(movieList)
				.delayElements(Duration.ofSeconds(2))
				.flatMap(this.repository::save)
				.doOnComplete(() -> System.out.println("Complete"))
				.subscribe();
	}
}
