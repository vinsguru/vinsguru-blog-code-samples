package com.vinsguru.common.dto;

import com.vinsguru.common.domain.TripStatus;

public class TripResponseDto extends TripRequestDto {

    private DriverDto driverDto;
    private TripStatus tripStatus;
    private double price;

    public TripResponseDto() {}

    public TripResponseDto(TripRequestDto tripRequestDto){
        super(tripRequestDto.getUserId(), tripRequestDto.getTripDto().getSource(), tripRequestDto.getTripDto().getDestination());
    }

    public DriverDto getDriverDto() {
        return driverDto;
    }

    public void setDriverDto(DriverDto driverDto) {
        this.driverDto = driverDto;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
