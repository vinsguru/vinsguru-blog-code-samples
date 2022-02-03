package com.vinsguru.webfluxsse.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Sinks;

@Service
public class JokePublisher {

    @Autowired
    private WebClient webClient;

    @Autowired
    private Sinks.Many<Joke> sink;

    @Scheduled(fixedRate = 3000)
    public void publish(){
        this.webClient
                .get()
                .retrieve()
                .bodyToMono(Joke.class)
                .subscribe(this.sink::tryEmitNext);
    }

}
