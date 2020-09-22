package com.vinsguru.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class RestSquareService {

    @Value("${rest.square.service.endpoint}")
    private String baseUrl;

    private WebClient webClient;

    @PostConstruct
    private void init(){
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Flux<Object> getUnaryResponse(int number){
        return Flux.range(1, number)
                .flatMap(i -> this.webClient.get()
                        .uri(String.format("/api/square/unary/%d", i))
                        .retrieve()
                        .bodyToMono(Object.class)
                        .map(o -> (Object) Map.of(i, o)))
                .subscribeOn(Schedulers.boundedElastic());
    }

}
