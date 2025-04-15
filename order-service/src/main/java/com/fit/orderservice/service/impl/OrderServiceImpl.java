package com.fit.orderservice.service.impl;

import com.fit.orderservice.dto.request.OrderRequest;
import com.fit.orderservice.dto.response.OrderResponse;
import com.fit.orderservice.entity.Order;
import com.fit.orderservice.entity.OrderDetail;
import com.fit.orderservice.exception.ResourceNotFoundException;
import com.fit.orderservice.mapper.OrderMapper;
import com.fit.orderservice.repository.OrderRepository;
import com.fit.orderservice.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setOrderDate(Instant.now());
        order.setTotal(orderRequest.getTotal());
        order.setStatus(orderRequest.getStatus());

        List<OrderDetail> orderDetails = orderRequest.getOrderDetails().stream()
                .map(detail -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProductId(detail.getProductId());
                    orderDetail.setQuantity(detail.getQuantity());
                    orderDetail.setPrice(detail.getPrice());
                    orderDetail.setOrder(order);
                    return orderDetail;
                })
                .collect(Collectors.toList());

        order.setOrderDetails(orderDetails);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse updateOrder(UUID id, OrderRequest orderRequest) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        existingOrder.setCustomerId(orderRequest.getCustomerId());
        existingOrder.setTotal(orderRequest.getTotal());
        existingOrder.setStatus(orderRequest.getStatus());

        // Update order details
        List<OrderDetail> updatedOrderDetails = orderRequest.getOrderDetails().stream()
                .map(detail -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProductId(detail.getProductId());
                    orderDetail.setQuantity(detail.getQuantity());
                    orderDetail.setPrice(detail.getPrice());
                    orderDetail.setOrder(existingOrder);
                    return orderDetail;
                })
                .collect(Collectors.toList());

        existingOrder.setOrderDetails(updatedOrderDetails);
        Order updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.toOrderResponse(updatedOrder);
    }

    @Override
    public void deleteOrder(UUID id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}