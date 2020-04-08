package com.vinsguru.userservice.service;

import com.vinsguru.rating.client.RatingService;
import com.vinsguru.rating.client.dto.Rating;
import com.vinsguru.rating.client.dto.RatingRequest;
import com.vinsguru.rating.client.dto.RatingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Service
public class UserService {

    private WebClient webClient;

    @PostConstruct
    public void init(){
        this.webClient = WebClient.builder()
                                .baseUrl("http://localhost:8080/rating-service")
                                .build();
    }

    public Mono<RatingResponse> rateProduct(int userId, int productId, int rating){
        RatingRequest ratingRequest = RatingRequest.newRequest(req -> {
            req.setUserId(userId);
            req.setProductId(productId);
            req.setRating(Rating.getRating(rating));
        });
        return RatingService.client(this.webClient)
                            .rate(ratingRequest);
    }
}
