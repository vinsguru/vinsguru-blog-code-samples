package com.vinsguru.userservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {

    @Id
    private String id;
    private String name;
    private List<UserTrip> userTrips;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserTrip> getUserTrips() {
        return userTrips;
    }

    public void setUserTrips(List<UserTrip> userTrips) {
        this.userTrips = userTrips;
    }
}
