package com.vinsguru.dto;

import com.vinsguru.events.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class OrderResponseDto {

    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private Integer amount;
    private OrderStatus status;

}
