package com.vinsguru.rsocketsecurity.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class SecuredController {

    @PreAuthorize("hasRole('USER')")
    @MessageMapping("user-request")
    public Mono<Integer> findDouble(final int requestNo) {
        return Mono.just(requestNo * 2);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MessageMapping("admin-request")
    public Mono<Integer> findTriple(final int requestNo) {
        return Mono.just(requestNo * 3);
    }

}
