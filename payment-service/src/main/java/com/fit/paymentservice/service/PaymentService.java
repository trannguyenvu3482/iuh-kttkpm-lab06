package com.fit.paymentservice.service;

import com.fit.paymentservice.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAllPayments();

    PaymentDto getPaymentById(Long id);

    List<PaymentDto> getPaymentsByOrderId(Long orderId);

    List<PaymentDto> getPaymentsByCustomerId(Long customerId);

    PaymentDto createPayment(PaymentDto paymentDto);

    PaymentDto updatePayment(Long id, PaymentDto paymentDto);

    void deletePayment(Long id);
}