package com.vinsguru.orderservice.controller;

import com.vinsguru.orderservice.entity.PurchaseOrder;
import com.vinsguru.orderservice.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-service")
public class OrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/all")
    public List<PurchaseOrder> getAllOrders(){
        return this.purchaseOrderService.getPurchaseOrders();
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody PurchaseOrder purchaseOrder){
        this.purchaseOrderService.createPurchaseOrder(purchaseOrder);
    }

}
