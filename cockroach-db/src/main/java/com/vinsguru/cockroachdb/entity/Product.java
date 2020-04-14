package com.vinsguru.cockroachdb.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@ToString
public class Product {

    @Id
    private UUID id;
    private String description;
    private double price;

}
