package com.vinsguru.rating.service;

import com.vinsguru.dto.ProductRatingDto;
import com.vinsguru.dto.ReviewDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class RatingService {

    private Map<Integer, ProductRatingDto> map;

    @PostConstruct
    private void init(){

        // product 1
        ProductRatingDto ratingDto1 = ProductRatingDto.of(4.5,
                List.of(
                        ReviewDto.of("vins", "guru", 1, 5, "excellent"),
                        ReviewDto.of("marshall", "mathers", 1, 4, "decent")
                )
        );

        // product 2
        ProductRatingDto ratingDto2 = ProductRatingDto.of(4,
                List.of(
                        ReviewDto.of("slim", "shady", 2, 5, "best"),
                        ReviewDto.of("fifty", "cent", 2, 3, "")
                )
        );

        // map as db
        this.map = Map.of(
                1, ratingDto1,
                2, ratingDto2
        );

    }

    public ProductRatingDto getRatingForProduct(int productId) {
        return this.map.getOrDefault(productId, new ProductRatingDto());
    }

}
