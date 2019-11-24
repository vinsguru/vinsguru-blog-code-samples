package com.vinsguru.materializedview.service;

import com.vinsguru.materializedview.dto.PurchaseOrderSummaryDto;

import java.util.List;

public interface PurchaseOrderService {
    void placeOrder(int userIndex, int productIndex);
    List<PurchaseOrderSummaryDto> getSaleSummary();
}
