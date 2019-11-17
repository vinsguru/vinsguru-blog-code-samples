package com.vinsguru.userservice.service;


import com.vinsguru.userservice.dto.UserDto;

public interface UserService {
    Long createUser(UserDto userDto);
    void updateUser(UserDto userDto);
}
