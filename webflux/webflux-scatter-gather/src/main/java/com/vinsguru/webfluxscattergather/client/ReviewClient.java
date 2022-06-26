package com.vinsguru.webfluxscattergather.client;

import com.vinsguru.webfluxscattergather.dto.Review;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class ReviewClient {

    private final WebClient client;

    public ReviewClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:3000/reviews").build();
    }

    public Mono<List<Review>> getReviews(Integer productId){
        return this.client
                .get()
                .uri(b -> b.queryParam("productId", productId).build())
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .onErrorReturn(Collections.emptyList()); // in case of error, switch it to empty list
    }

}
