package com.vinsguru.rest.controller;

import com.vinsguru.rest.service.RestSquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestSquareController {

    @Autowired
    private RestSquareService squareService;

    @GetMapping("/rest/square/unary/{number}")
    public int getSquareUnary(@PathVariable int number){
        return this.squareService.getSquareUnary(number);
    }

}
