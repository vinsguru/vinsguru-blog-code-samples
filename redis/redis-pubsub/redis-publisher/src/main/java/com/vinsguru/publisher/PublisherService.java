package com.vinsguru.publisher;

import com.vinsguru.dto.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Service
public class PublisherService {

    private static final String JOKE_API_ENDPOINT = "https://official-joke-api.appspot.com/jokes/random";
    private WebClient webClient;

    @Autowired
    private ReactiveRedisOperations<String, Joke> redisTemplate;

    @Value("${topic.name:joke-channel}")
    private String topic;

    @PostConstruct
    private void init(){
        this.webClient = WebClient.builder()
                .baseUrl(JOKE_API_ENDPOINT)
                .build();
    }

    @Scheduled(fixedRate = 3000)
    public void publish(){
        this.webClient.get()
                .retrieve()
                .bodyToMono(Joke.class)
                .flatMap(joke -> this.redisTemplate.convertAndSend(topic, joke))
                .subscribe();
    }

}
