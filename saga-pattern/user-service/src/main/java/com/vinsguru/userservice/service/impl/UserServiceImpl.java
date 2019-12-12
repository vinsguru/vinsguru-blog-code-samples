package com.vinsguru.userservice.service.impl;

import com.vinsguru.common.dto.TripRequestDto;
import com.vinsguru.common.dto.TripResponseDto;
import com.vinsguru.common.util.JsonUtil;
import com.vinsguru.userservice.entity.UserTripEvent;
import com.vinsguru.userservice.repository.UserRepository;
import com.vinsguru.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void createTrip(TripRequestDto tripRequestDto) {
        this.kafkaTemplate.sendDefault(tripRequestDto.getUserId(), JsonUtil.objectToString(tripRequestDto));
    }

    @Override
    @Transactional
    @KafkaListener(topics = "trip-response-event")
    public void updateTrip(TripResponseDto tripResponseDto) {
        System.out.println(JsonUtil.objectToString(tripResponseDto));
        this.userRepository
                .findById(tripResponseDto.getUserId())
                .flatMap(
                    user -> user.getUserTrips()
                                .stream()
                                .filter(userTrip -> userTrip.getId().equals(tripResponseDto.getTripDto().getId()))
                                .findFirst()
                ).ifPresent(userTrip -> {
                   userTrip.getUserTripEvents().add(new UserTripEvent(new Date(), tripResponseDto.getTripStatus()));
                });
    }

}
