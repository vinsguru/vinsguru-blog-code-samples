package com.vinsguru.scattergather;

import com.vinsguru.model.FlightSearchRequest;
import com.vinsguru.model.FlightSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("flight")
public class FlightSearchController {

    @Autowired
    private ScatterGatherService service;

    @GetMapping("/{from}/{to}")
    public Mono<FlightSearchResponse> search(@PathVariable String from, @PathVariable String to){
        return this.service.broadcast(FlightSearchRequest.of(from, to));
    }

}
