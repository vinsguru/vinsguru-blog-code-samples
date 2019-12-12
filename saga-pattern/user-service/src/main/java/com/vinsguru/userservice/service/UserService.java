package com.vinsguru.userservice.service;

import com.vinsguru.common.dto.TripRequestDto;
import com.vinsguru.common.dto.TripResponseDto;

public interface UserService {
    void createTrip(TripRequestDto tripRequestDto);
    void updateTrip(TripResponseDto tripResponseDto);
}
