package com.vinsguru.webclientfeign.config;

import com.vinsguru.webclientfeign.dto.MovieDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(value = "movie-service", url = "${movie.service.url}")
public interface MovieClient {

    @RequestMapping(method = RequestMethod.GET, value = "movies")
    Flux<MovieDto> getAllMovies();

    @RequestMapping(method = RequestMethod.GET, value = "movies/{movieId}")
    Mono<MovieDto> getMovie(@PathVariable("movieId") Integer movieId);

    @RequestMapping(method = RequestMethod.POST, value = "movies/")
    Mono<MovieDto> saveMovie(MovieDto movieDto);

    @RequestMapping(method = RequestMethod.PUT, value = "movies/{movieId}")
    Mono<Void> updateMovie(@PathVariable("movieId") Integer movieId, MovieDto movieDto);

    @RequestMapping(method = RequestMethod.DELETE, value = "movies/{movieId}")
    Mono<Void> deleteMovie(@PathVariable("movieId") Integer movieId);

}
