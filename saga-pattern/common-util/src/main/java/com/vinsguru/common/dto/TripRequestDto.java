package com.vinsguru.common.dto;

public class TripRequestDto {

    private String userId;
    private TripDto tripDto;

    public TripRequestDto() {}

    public TripRequestDto(String userId, String source, String destination) {
        this.userId = userId;
        this.tripDto = new TripDto(source, destination);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TripDto getTripDto() {
        return tripDto;
    }

    public void setTripDto(TripDto tripDto) {
        this.tripDto = tripDto;
    }
}
