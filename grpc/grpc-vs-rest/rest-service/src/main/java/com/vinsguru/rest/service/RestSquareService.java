package com.vinsguru.rest.service;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class RestSquareService {

    public int getSquareUnary(int number){
        return number * number;
    }

    public Flux<Object> getSquareStream(int number){
        return Flux.create(fluxSink -> {
            for (int i = 1; i <= number ; i++) {
                fluxSink.next(Map.entry(i, (i*i)));
            }
            fluxSink.complete();
        });
    }

}
