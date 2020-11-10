package com.vinsguru.rating.controller;

import com.vinsguru.dto.ProductRatingDto;
import com.vinsguru.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ProductRatingDto getRating(@PathVariable int prodId) throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(10, 5000));
        return this.ratingService.getRatingForProduct(prodId);
    }

}
