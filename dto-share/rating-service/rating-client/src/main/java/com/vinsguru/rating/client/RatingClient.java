package com.vinsguru.rating.client;

import com.vinsguru.rating.client.dto.RatingRequest;
import com.vinsguru.rating.client.dto.RatingResponse;
import reactor.core.publisher.Mono;

public interface RatingClient {

    Mono<RatingResponse> rate(RatingRequest ratingRequest);

}
