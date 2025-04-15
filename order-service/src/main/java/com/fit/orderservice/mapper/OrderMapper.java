package com.fit.orderservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fit.orderservice.client.ProductClient;
import com.fit.orderservice.dto.response.ApiResponse;
import com.fit.orderservice.dto.response.OrderDetailResponse;
import com.fit.orderservice.dto.response.OrderResponse;
import com.fit.orderservice.dto.response.ProductResponse;
import com.fit.orderservice.entity.Order;
import com.fit.orderservice.entity.OrderDetail;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ProductClient productClient;

    public OrderResponse toOrderResponse(Order order) {
        List<OrderDetailResponse> orderDetailResponses = order.getOrderDetails().stream()
                .map(this::toOrderDetailResponse)
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .orderDate(order.getOrderDate())
                .total(order.getTotal())
                .status(order.getStatus())
                .orderDetails(orderDetailResponses)
                .build();
    }

    private OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {
        ApiResponse<ProductResponse> productResponse = productClient.getProductById(orderDetail.getProductId());
        ProductResponse product = productResponse.getData();

        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .productId(orderDetail.getProductId())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .product(product)
                .build();
    }
}