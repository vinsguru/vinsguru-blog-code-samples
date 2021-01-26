package com.vinsguru.server.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
public class ServerController {

    @MessageMapping("square-calculator")
    public Mono<Integer> square(Mono<Integer> input){
        return input
                .doOnNext(i -> System.out.println("Received : " + i))
                .delayElement(Duration.ofMillis(500))
                .map(i -> i * i);
    }

}
