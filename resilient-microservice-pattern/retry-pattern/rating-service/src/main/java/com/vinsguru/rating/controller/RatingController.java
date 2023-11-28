package com.vinsguru.rating.controller;

import com.vinsguru.dto.ProductRatingDto;
import com.vinsguru.rating.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@AllArgsConstructor
@RequestMapping("ratings")
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("{prodId}")
    public ResponseEntity<ProductRatingDto> getRating(@PathVariable int prodId) {
        var productRatingDto = this.ratingService.getRatingForProduct(prodId);
        return this.failAtRandom(productRatingDto);
    }

    private ResponseEntity<ProductRatingDto> failAtRandom(ProductRatingDto productRatingDto){
        var random = ThreadLocalRandom.current().nextInt(1, 5);
        return switch (random){
            case 1,2 -> ResponseEntity.status(500).build();
            case 3 -> ResponseEntity.badRequest().build();
            default -> ResponseEntity.ok(productRatingDto);
        };
    }

}
