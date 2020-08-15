package com.vinsguru.rating.client.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RatingResponse {

    private int productId;
    private double avgRating;

}
