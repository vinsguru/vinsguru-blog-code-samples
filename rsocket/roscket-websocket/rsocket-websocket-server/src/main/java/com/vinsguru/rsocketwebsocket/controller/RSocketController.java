package com.vinsguru.rsocketwebsocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class RSocketController {

    @MessageMapping("number.stream")
    public Flux<Integer> responseStream(Integer number) {
        return Flux.range(1, number)
                .delayElements(Duration.ofSeconds(1));
    }

    @MessageMapping("number.channel")
    public Flux<Long> biDirectionalStream(Flux<Long> numberFlux) {
        return numberFlux
                .map(n -> n * n)
                .onErrorReturn(-1L);
    }

}
