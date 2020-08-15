package com.vinsguru.dto;

import com.vinsguru.enums.OrderStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderResponseDTO {

    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private Double amount;
    private OrderStatus status;

}
