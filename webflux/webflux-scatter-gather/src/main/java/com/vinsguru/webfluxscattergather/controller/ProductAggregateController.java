package com.vinsguru.webfluxscattergather.controller;

import com.vinsguru.webfluxscattergather.dto.ProductAggregate;
import com.vinsguru.webfluxscattergather.service.ProductAggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductAggregateController {

    @Autowired
    private ProductAggregatorService service;

    @GetMapping("{productId}")
    public Mono<ResponseEntity<ProductAggregate>> getProduct(@PathVariable Integer productId){
        return this.service.getProduct(productId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
