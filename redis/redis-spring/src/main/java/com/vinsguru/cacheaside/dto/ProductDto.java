package com.vinsguru.cacheaside.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDto {

    private Integer id;
    private String description;
    private Integer price;
    private Integer quantityAvailable;

}
