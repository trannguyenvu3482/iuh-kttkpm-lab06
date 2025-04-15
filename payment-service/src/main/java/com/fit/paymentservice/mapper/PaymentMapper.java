package com.fit.paymentservice.mapper;

import com.fit.paymentservice.dto.PaymentDto;
import com.fit.paymentservice.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentDto toDto(Payment payment) {
        if (payment == null) {
            return null;
        }

        return new PaymentDto(
                payment.getId(),
                payment.getOrderId(),
                payment.getCustomerId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getTransactionId(),
                payment.getPaymentDate(),
                payment.getPaymentMethod());
    }

    public Payment toEntity(PaymentDto paymentDto) {
        if (paymentDto == null) {
            return null;
        }

        return new Payment(
                paymentDto.getId(),
                paymentDto.getOrderId(),
                paymentDto.getCustomerId(),
                paymentDto.getAmount(),
                paymentDto.getStatus(),
                paymentDto.getTransactionId(),
                paymentDto.getPaymentDate(),
                paymentDto.getPaymentMethod());
    }
}