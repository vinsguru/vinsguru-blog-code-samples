package com.vinsguru.rsocket.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
public class RequestController {

    @Autowired
    private RequestProcessor requestProcessor;

    @MessageMapping("place-request")
    public Mono<UUID> task(String request, RSocketRequester rSocketRequester){
        UUID uuid = UUID.randomUUID();
        this.requestProcessor.processRequests(rSocketRequester, uuid);
        return Mono.just(uuid);
    }

}
