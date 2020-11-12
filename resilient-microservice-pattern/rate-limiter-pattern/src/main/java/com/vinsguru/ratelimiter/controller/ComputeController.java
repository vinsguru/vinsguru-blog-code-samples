package com.vinsguru.ratelimiter.controller;

import com.vinsguru.ratelimiter.dto.ComputeResponse;
import com.vinsguru.ratelimiter.dto.ResponseType;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeController {

    @GetMapping("/double/{input}")
    public ComputeResponse doubleValue(@PathVariable int input){
        return ComputeResponse.of(input, 2*input, ResponseType.SUCCESS, Strings.EMPTY);
    }

    @GetMapping("/square/{input}")
    @RateLimiter(name = "squareLimit", fallbackMethod = "squareErrorResponse")
    public ComputeResponse getValue(@PathVariable int input){
        return ComputeResponse.of(input, input * input, ResponseType.SUCCESS, Strings.EMPTY);
    }

    public ComputeResponse squareErrorResponse(int input, Throwable throwable){
        return ComputeResponse.of(input, -1, ResponseType.FAILURE, throwable.getMessage());
    }

}
