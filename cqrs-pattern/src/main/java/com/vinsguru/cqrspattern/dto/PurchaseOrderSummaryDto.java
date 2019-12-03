package com.vinsguru.cqrspattern.dto;

public class PurchaseOrderSummaryDto {
    private String state;
    private double totalSale;

    public PurchaseOrderSummaryDto(){
    }

    public PurchaseOrderSummaryDto(String state, double totalSale) {
        this.state = state;
        this.totalSale = totalSale;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }
}
