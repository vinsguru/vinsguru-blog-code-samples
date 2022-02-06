package com.vinsguru.cacheaside.entity;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Table
public class Product {

    @Id
    private Integer id;
    private String description;
    private Integer price;
    private Integer qtyAvailable;

}
