package com.fit.shippingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fit.shippingservice.entity.Shipment;
import com.fit.shippingservice.entity.ShipmentTracking;

@Repository
public interface ShipmentTrackingRepository extends JpaRepository<ShipmentTracking, Long> {
    List<ShipmentTracking> findByShipmentOrderByTimestampDesc(Shipment shipment);

    List<ShipmentTracking> findByShipmentIdOrderByTimestampDesc(Long shipmentId);
}