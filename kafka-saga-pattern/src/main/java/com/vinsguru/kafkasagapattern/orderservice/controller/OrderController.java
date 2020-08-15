package com.vinsguru.kafkasagapattern.orderservice.controller;

import com.vinsguru.kafkasagapattern.model.dto.OrderRequestDTO;
import com.vinsguru.kafkasagapattern.model.dto.OrderResponseDTO;
import com.vinsguru.kafkasagapattern.orderservice.entity.PurchaseOrder;
import com.vinsguru.kafkasagapattern.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDTO dto){
        return this.orderService.createOrder(dto);
    }

    @GetMapping("/all")
    public List<OrderResponseDTO> getOrders(){
        return this.orderService.getAll();
    }

}
