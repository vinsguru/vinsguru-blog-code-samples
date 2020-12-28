package com.vinsguru.rsocket.server.controller;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RequestProcessor {

    public void processRequests(RSocketRequester rSocketRequester, UUID uuid){
        Mono.just("Your request " + uuid + "  is completed")
                .delayElement(Duration.ofSeconds(ThreadLocalRandom.current().nextInt(5, 10)))
                .flatMap(m -> rSocketRequester.route("request-status-callback").data(m).send())
                .subscribe();
    }

}
