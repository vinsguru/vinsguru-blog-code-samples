package com.vinsguru.reactivevalidation.controller;

import com.vinsguru.reactivevalidation.dto.UserDto;
import com.vinsguru.reactivevalidation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Mono<UserDto> register(@Valid @RequestBody Mono<UserDto> userDtoMono){
        return this.userService.registerUser(userDtoMono);
    }

}
