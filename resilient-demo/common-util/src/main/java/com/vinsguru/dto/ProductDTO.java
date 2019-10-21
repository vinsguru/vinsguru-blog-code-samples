package com.vinsguru.dto;

public class ProductDTO {

    private int productId;
    private String description;
    private double price;
    private ProductRatingDTO productRating;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public ProductRatingDTO getProductRating() {
        return productRating;
    }

    public void setProductRating(ProductRatingDTO productRating) {
        this.productRating = productRating;
    }
}
