package com.vinsguru.userservice.controller;

import com.vinsguru.userservice.dto.UserDto;
import com.vinsguru.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Long createUser(@RequestBody UserDto userDto){
        return this.userService.createUser(userDto);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody UserDto userDto){
        this.userService.updateUser(userDto);
    }

}
