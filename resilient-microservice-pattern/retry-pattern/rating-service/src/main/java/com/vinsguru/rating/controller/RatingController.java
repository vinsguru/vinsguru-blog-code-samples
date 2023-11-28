package com.vinsguru.rating.controller;

import com.vinsguru.dto.ProductRatingDto;
import com.vinsguru.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("{prodId}")
    public ResponseEntity<ProductRatingDto> getRating(@PathVariable int prodId) {
        ProductRatingDto productRatingDto = this.ratingService.getRatingForProduct(prodId);
        return this.failAtRandom(productRatingDto);
    }

    private ResponseEntity<ProductRatingDto> failAtRandom(ProductRatingDto productRatingDto){
        int random = ThreadLocalRandom.current().nextInt(1, 5);
        return switch (random){
            case 1,2 -> ResponseEntity.status(500).build();
            case 3 -> ResponseEntity.badRequest().build();
            default -> ResponseEntity.ok(productRatingDto);
        };
    }

}
