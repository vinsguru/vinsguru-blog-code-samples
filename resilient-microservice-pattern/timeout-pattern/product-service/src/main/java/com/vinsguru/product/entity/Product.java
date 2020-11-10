package com.vinsguru.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Product {

    private int productId;
    private String description;
    private double price;

}
