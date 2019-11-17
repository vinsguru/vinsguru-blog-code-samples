package com.vinsguru.orderservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinsguru.orderservice.entity.PurchaseOrder;
import com.vinsguru.orderservice.entity.User;
import com.vinsguru.orderservice.repository.PurchaseOrderRepository;
import com.vinsguru.orderservice.service.UserServiceEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceEventHandlerImpl implements UserServiceEventHandler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @KafkaListener(topics = "user-service-event")
    public void consume(String userStr) {
        try{
            User user = OBJECT_MAPPER.readValue(userStr, User.class);
            this.updateUser(user);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        List<PurchaseOrder> userOrders = this.purchaseOrderRepository.findByUserId(user.getId());
        userOrders.forEach(p -> p.setUser(user));
        this.purchaseOrderRepository.saveAll(userOrders);
    }
}
