package com.vinsguru.rest.controller;

import com.vinsguru.rest.service.RestSquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class RestSquareController {

    @Autowired
    private RestSquareService squareService;

    @GetMapping("/api/square/unary/{number}")
    public int getSquareUnary(@PathVariable int number){
        return this.squareService.getSquareUnary(number);
    }

    @GetMapping("/api/square/stream/{number}")
    public Flux<Object> getSquareStream(@PathVariable int number){
        return this.squareService.getSquareStream(number);
    }

}
