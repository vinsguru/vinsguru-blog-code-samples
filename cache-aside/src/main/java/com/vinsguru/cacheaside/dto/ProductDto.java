package com.vinsguru.cacheaside.dto;

public class ProductDto {

    private long id;
    private String description;
    private double price;
    private long quantityAvailable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(long quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
