package com.vinsguru.userservice.entity;

import com.vinsguru.common.domain.TripStatus;

import java.util.Date;

public class UserTripEvent {

    private Date time;
    private TripStatus tripStatus;

    public UserTripEvent(Date time, TripStatus tripStatus) {
        this.time = time;
        this.tripStatus = tripStatus;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }
}
