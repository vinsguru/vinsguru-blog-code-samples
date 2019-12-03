package com.vinsguru.cqrspattern.dto;

public class OrderCommandDto {

    private int userIndex;
    private int productIndex;

    public int getUserIndex() {
        return userIndex;
    }

    public void setUserIndex(int userIndex) {
        this.userIndex = userIndex;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }
}
