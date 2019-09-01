package com.vinsguru.dto;

import java.util.Date;

public class CarDto {

    private long id;
    private String make;
    private int numOfSeats;
    private Date releaseDate;
    private EngineDto engineDto;

    //getters & setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public EngineDto getEngineDto() {
        return engineDto;
    }

    public void setEngineDto(EngineDto engineDto) {
        this.engineDto = engineDto;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", numOfSeats=" + numOfSeats +
                ", releaseDate=" + releaseDate +
                ", engineDto=" + engineDto +
                '}';
    }
}
