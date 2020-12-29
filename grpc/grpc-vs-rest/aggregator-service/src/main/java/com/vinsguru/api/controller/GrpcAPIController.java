package com.vinsguru.api.controller;

import com.vinsguru.api.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("grpc")
public class GrpcAPIController {

    @Autowired
    private GrpcService service;

    @GetMapping("/unary/{number}")
    public Object getResponseUnary(@PathVariable int number){
        return this.service.getSquareResponseUnary(number);
    }

    @GetMapping("/stream/{number}")
    public Object getResponseStream(@PathVariable int number){
        return this.service.getSquareResponseStream(number);
    }

}
