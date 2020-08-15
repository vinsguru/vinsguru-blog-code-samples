package com.vinsguru.rating.client;

import com.vinsguru.rating.client.dto.RatingRequest;
import com.vinsguru.rating.client.dto.RatingResponse;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class RatingService {

    public static RatingClient client(WebClient webClient){
        return new ClientImpl(webClient);
    }

    private static class ClientImpl implements RatingClient {

        private final WebClient webClient;

        public ClientImpl(WebClient webClient) {
            this.webClient = webClient;
        }

        @Override
        public Mono<RatingResponse> rate(RatingRequest ratingRequest) {
            this.validate(ratingRequest);
            return webClient
                    .post()
                    .uri("/rate")
                    .body(BodyInserters.fromValue(ratingRequest))
                    .retrieve()
                    .bodyToMono(RatingResponse.class);
        }

        private void validate(RatingRequest ratingRequest){
            Objects.requireNonNull(ratingRequest.getProductId());
            Objects.requireNonNull(ratingRequest.getUserId());
            Objects.requireNonNull(ratingRequest.getRating());
        }
    }

}
