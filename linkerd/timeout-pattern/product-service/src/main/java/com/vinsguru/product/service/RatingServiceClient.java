package com.vinsguru.product.service;

import com.vinsguru.dto.ProductRatingDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class RatingServiceClient {

    @Value("${rating.service.endpoint}")
    private String ratingService;

    private WebClient webClient;

    @PostConstruct
    private void init(){
        this.webClient = WebClient.builder()
                .baseUrl(ratingService)
                .build();
    }

    public Mono<ProductRatingDto> getProductRatingDto(int productId){
        return this.webClient
                .get()
                .uri(String.valueOf(productId))
                .retrieve()
                .bodyToMono(ProductRatingDto.class)
                .onErrorReturn(ProductRatingDto.of(0, Collections.emptyList()));
    }

}
