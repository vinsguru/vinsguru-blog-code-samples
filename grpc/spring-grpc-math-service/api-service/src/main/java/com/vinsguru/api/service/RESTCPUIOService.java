package com.vinsguru.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class RESTCPUIOService {

    @Value("${rest.square.service.endpoint}")
    private String baseUrl;

    private WebClient webClient;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    private void init(){
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Flux<Object> getUnaryResponse(int number){
        return Flux.create(fluxSink -> {
            for (int i = 1; i <= number ; i++) {
                final int j = i;
                ResponseEntity<Integer> entity = this.restTemplate.getForEntity(this.baseUrl + String.format("/api/square/unary/%d", j), Integer.class);
                fluxSink.next(Map.of(j, entity.getBody()));
            }
            fluxSink.complete();
        });
    }

    public Flux<Object> getStreamResponse(int number){
        return this.webClient.get()
                .uri(String.format("/api/square/stream/%d", number))
                .retrieve()
                .bodyToFlux(Object.class);
    }

}
