package com.vinsguru.api.controller;

import com.vinsguru.api.service.RESTCPUIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("rest")
public class RESTAPIController {

    @Autowired
    private RESTCPUIOService service;

    @GetMapping("/square/unary/{number}")
    public Flux<Object> getResponseUnary(@PathVariable int number){
        return this.service.getUnaryResponse(number);
    }

    @GetMapping("/square/stream/{number}")
    public Flux<Object> getResponseStream(@PathVariable int number){
        return this.service.getStreamResponse(number);
    }

}
