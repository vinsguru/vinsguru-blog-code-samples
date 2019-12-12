package com.vinsguru.userservice.controller;

import com.vinsguru.common.dto.TripRequestDto;
import com.vinsguru.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createTrip(@RequestBody TripRequestDto tripRequestDto){
        this.userService.createTrip(tripRequestDto);
    }

}
