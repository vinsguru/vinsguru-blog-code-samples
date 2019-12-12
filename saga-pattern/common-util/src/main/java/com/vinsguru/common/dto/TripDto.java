package com.vinsguru.common.dto;

public class TripDto {

    private String id;
    private String source;
    private String destination;

    public TripDto(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

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
}
