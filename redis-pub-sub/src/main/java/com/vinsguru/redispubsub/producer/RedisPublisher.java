package com.vinsguru.redispubsub.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RedisPublisher {

    private static final String JOKE_API_ENDPOINT = "https://official-joke-api.appspot.com/jokes/random";
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private RedisTemplate<String, Joke> redisTemplate;

    @Autowired
    private Topic topic;

    @Scheduled(fixedRate = 3000)
    public void publish(){
        this.redisTemplate.convertAndSend(topic.getTopic(), getJoke());
    }

    public Joke getJoke(){
        ResponseEntity<Joke> entity = this.restTemplate.getForEntity(JOKE_API_ENDPOINT, Joke.class);
        return entity.getStatusCode().is2xxSuccessful() ? entity.getBody() : new Joke();
    }

}
