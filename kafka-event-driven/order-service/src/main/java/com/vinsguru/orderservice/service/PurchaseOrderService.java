package com.vinsguru.orderservice.service;


import com.vinsguru.orderservice.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseOrder> getPurchaseOrders();
    void createPurchaseOrder(PurchaseOrder purchaseOrder);
}
