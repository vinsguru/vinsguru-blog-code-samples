package com.vinsguru.service;

import com.vinsguru.rating.client.dto.Rating;
import com.vinsguru.rating.client.dto.RatingRequest;
import com.vinsguru.rating.client.dto.RatingResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RatingService {

    private Map<Integer, List<ProductRating>> map;

    @PostConstruct
    public void init(){
        this.map = Map.of(
                1, new ArrayList<>(),
                2, new ArrayList<>()
        );
        this.map.get(1).add(new ProductRating(1, Rating.FOUR));
        this.map.get(1).add(new ProductRating(2, Rating.FOUR));
        this.map.get(1).add(new ProductRating(3, Rating.FIVE));

        this.map.get(2).add(new ProductRating(1, Rating.THREE));
        this.map.get(2).add(new ProductRating(2, Rating.TWO));
        this.map.get(2).add(new ProductRating(3, Rating.FIVE));
    }

    public RatingResponse rate(RatingRequest ratingRequest){
        List<ProductRating> productRatings = this.map.get(ratingRequest.getProductId());
        productRatings.add(new ProductRating(ratingRequest.getUserId(), ratingRequest.getRating()));
        RatingResponse ratingResponse = new RatingResponse();
        ratingResponse.setProductId(ratingRequest.getProductId());
        double avgRating = productRatings
                .stream()
                .map(ProductRating::getRating)
                .mapToInt(Rating::ordinal)
                .map(rating -> rating + 1)
                .average()
                .orElse(0);
        ratingResponse.setAvgRating(avgRating);
        return ratingResponse;
    }

    @Data
    @ToString
    @AllArgsConstructor
    private static class ProductRating {
        private int userId;
        private Rating rating;
    }

}
