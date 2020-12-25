package com.vinsguru.controller;

import com.vinsguru.dto.ResultDto;
import com.vinsguru.service.GrpcSquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("grpc")
public class SquareServiceController {

    @Autowired
    private GrpcSquareService service;

    @GetMapping("/{number}")
    public Flux<ResultDto> getResponseUnary(@PathVariable int number){
        return this.service.getSquareResponseUnary(number);
    }

}
