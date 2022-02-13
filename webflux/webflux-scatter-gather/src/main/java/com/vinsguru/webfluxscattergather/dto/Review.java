package com.vinsguru.webfluxscattergather.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Review {

    private String user;
    private Integer rating;
    private String comment;

}
