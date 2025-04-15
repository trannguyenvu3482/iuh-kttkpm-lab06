package com.fit.orderservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fit.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}