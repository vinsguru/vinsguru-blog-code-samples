package com.vinsguru.webclientfeign.config;

import com.vinsguru.webclientfeign.dto.MovieDto;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(value = "movie-service", url = "${movie.service.url}")
public interface MovieClient {

    @GetMapping("movies")
    Flux<MovieDto> getAllMovies();

    @GetMapping("movies/{movieId}")
    Mono<MovieDto> getMovie(@PathVariable("movieId") Integer movieId);

    @PostMapping("movies")
    Mono<MovieDto> saveMovie(MovieDto movieDto);

    @PutMapping("movies/{movieId}")
    Mono<Void> updateMovie(@PathVariable("movieId") Integer movieId, MovieDto movieDto);

    @DeleteMapping("movies/{movieId}")
    Mono<Void> deleteMovie(@PathVariable("movieId") Integer movieId);

}
