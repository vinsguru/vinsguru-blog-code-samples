package com.vinsguru.webfluxscattergather.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {

    private String id;
    private String description;
    private String category;

}
