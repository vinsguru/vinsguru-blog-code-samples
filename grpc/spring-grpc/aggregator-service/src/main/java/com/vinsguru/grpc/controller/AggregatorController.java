package com.vinsguru.grpc.controller;

import com.vinsguru.grpc.service.AggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class AggregatorController {

    @Autowired
    private AggregatorService aggregatorService;

    @GetMapping("/doubles/{number}")
    public Flux<Long> getAllDoubles(@PathVariable int number){
        return this.aggregatorService.getAllDoubles(number);
    }

}
