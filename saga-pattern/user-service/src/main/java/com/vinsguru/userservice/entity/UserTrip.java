package com.vinsguru.userservice.entity;

import java.util.List;

public class UserTrip {

    private String id;
    private String source;
    private String destination;
    private String driver;
    private double price;
    private List<UserTripEvent> userTripEvents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<UserTripEvent> getUserTripEvents() {
        return userTripEvents;
    }

    public void setUserTripEvents(List<UserTripEvent> userTripEvents) {
        this.userTripEvents = userTripEvents;
    }
}
