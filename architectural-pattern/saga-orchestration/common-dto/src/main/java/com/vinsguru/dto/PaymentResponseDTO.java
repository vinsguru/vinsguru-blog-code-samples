package com.vinsguru.dto;

import com.vinsguru.enums.PaymentStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentResponseDTO {
    private Integer userId;
    private UUID orderId;
    private Double amount;
    private PaymentStatus status;
}
