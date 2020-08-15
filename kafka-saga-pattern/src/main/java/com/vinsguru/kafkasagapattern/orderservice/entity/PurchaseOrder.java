package com.vinsguru.kafkasagapattern.orderservice.entity;

import com.vinsguru.kafkasagapattern.model.enums.OrderStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus status;

}
