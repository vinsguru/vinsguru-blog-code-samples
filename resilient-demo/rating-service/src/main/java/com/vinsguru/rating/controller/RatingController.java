package com.vinsguru.rating.controller;

import com.vinsguru.dto.ProductRatingDTO;
import com.vinsguru.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("v1")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/ratings/{prodId}")
    public ProductRatingDTO getRating(@PathVariable int prodId) throws InterruptedException {
        int sleepTime = 30_000;
        System.out.println("Rating service will respond in " + sleepTime + " ms");
        Thread.sleep(sleepTime);
        return this.ratingService.getRatingForProduct(prodId);
    }

}
