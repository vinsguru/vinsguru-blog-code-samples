package com.vinsguru.r2dbccallback.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
@NoArgsConstructor
public class Product {

    @Id
    private Integer id;
    private String description;
    private Double price;

    public Product(String description, Double price) {
        this.description = description;
        this.price = price;
    }
}
