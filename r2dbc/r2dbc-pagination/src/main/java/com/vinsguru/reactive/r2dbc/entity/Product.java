package com.vinsguru.reactive.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Product {

    @Id
    private Integer id;
    private String description;
    private Integer price;

}
