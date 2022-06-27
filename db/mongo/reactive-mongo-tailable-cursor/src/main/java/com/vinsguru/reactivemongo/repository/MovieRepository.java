package com.vinsguru.reactivemongo.repository;

import com.vinsguru.reactivemongo.entity.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

    @Tailable
    Flux<Movie> findByGenre(String genre);

}
