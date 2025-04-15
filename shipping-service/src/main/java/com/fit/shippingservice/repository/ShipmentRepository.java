package com.fit.shippingservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fit.shippingservice.entity.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByOrderId(Long orderId);

    List<Shipment> findByCustomerId(Long customerId);

    Optional<Shipment> findByTrackingNumber(String trackingNumber);
}