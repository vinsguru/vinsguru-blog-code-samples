package com.vinsguru.orderservice.service.impl;

import com.vinsguru.orderservice.entity.PurchaseOrder;
import com.vinsguru.orderservice.repository.PurchaseOrderRepository;
import com.vinsguru.orderservice.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<PurchaseOrder> getPurchaseOrders() {
        return this.purchaseOrderRepository.findAll();
    }

    @Override
    public void createPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrderRepository.save(purchaseOrder);
    }

}
