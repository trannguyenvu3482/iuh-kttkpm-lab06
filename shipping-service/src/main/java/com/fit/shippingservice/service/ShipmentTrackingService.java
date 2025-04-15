package com.fit.shippingservice.service;

import java.util.List;

import com.fit.shippingservice.dto.ShipmentTrackingDto;
import com.fit.shippingservice.entity.Shipment;
import com.fit.shippingservice.entity.ShipmentStatus;

public interface ShipmentTrackingService {
    List<ShipmentTrackingDto> getTrackingHistoryByShipmentId(Long shipmentId);

    ShipmentTrackingDto addTrackingUpdate(Shipment shipment, ShipmentStatus status, String location, String description,
            String updatedBy);

    ShipmentTrackingDto getLatestTrackingUpdate(Long shipmentId);
}