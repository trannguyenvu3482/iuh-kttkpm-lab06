package com.fit.orderservice.service;

import com.fit.orderservice.dto.request.OrderRequest;
import com.fit.orderservice.dto.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse getOrderById(UUID id);

    List<OrderResponse> getAllOrders();

    OrderResponse updateOrder(UUID id, OrderRequest orderRequest);

    void deleteOrder(UUID id);
}