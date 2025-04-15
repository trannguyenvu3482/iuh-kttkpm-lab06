package com.fit.payment_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fit.payment_service.entity.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long id;
    private Long orderId;
    private Long customerId;
    private BigDecimal amount;
    private PaymentStatus status;
    private String transactionId;
    private LocalDateTime paymentDate;
    private String paymentMethod;
}