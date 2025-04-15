package com.fit.payment_service.service;

import java.util.List;

import com.fit.payment_service.dto.PaymentDto;

public interface PaymentService {
    List<PaymentDto> getAllPayments();

    PaymentDto getPaymentById(Long id);

    List<PaymentDto> getPaymentsByOrderId(Long orderId);

    List<PaymentDto> getPaymentsByCustomerId(Long customerId);

    PaymentDto createPayment(PaymentDto paymentDto);

    PaymentDto updatePayment(Long id, PaymentDto paymentDto);

    void deletePayment(Long id);
}