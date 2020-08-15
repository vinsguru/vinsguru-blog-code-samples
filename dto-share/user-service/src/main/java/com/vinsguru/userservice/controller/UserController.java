package com.vinsguru.userservice.controller;

import com.vinsguru.rating.client.dto.RatingResponse;
import com.vinsguru.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user-service")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/{productId}/rate/{rating}")
    public Mono<RatingResponse> rate(@PathVariable int userId,
                                     @PathVariable int productId,
                                     @PathVariable int rating){
        return this.userService.rateProduct(userId, productId, rating);
    }


}
