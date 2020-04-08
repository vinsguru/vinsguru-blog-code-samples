package com.vinsguru.rating.client.dto;

import lombok.Data;
import lombok.ToString;

import java.util.function.Consumer;

@Data
@ToString
public class RatingRequest {

    private Integer userId;
    private Integer productId;
    private Rating rating;
    private String comment;

    public static RatingRequest newRequest(Consumer<RatingRequest> consumer){
        RatingRequest ratingRequest = new RatingRequest();
        consumer.accept(ratingRequest);
        return ratingRequest;
    }

}
