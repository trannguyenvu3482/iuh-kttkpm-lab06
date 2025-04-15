package com.fit.shippingservice.service;

import java.util.List;

import com.fit.shippingservice.dto.CreateShipmentRequest;
import com.fit.shippingservice.dto.ShipmentDto;
import com.fit.shippingservice.dto.UpdateShipmentStatusRequest;
import com.fit.shippingservice.entity.ShipmentStatus;

public interface ShipmentService {
    List<ShipmentDto> getAllShipments();

    ShipmentDto getShipmentById(Long id);

    ShipmentDto getShipmentByTrackingNumber(String trackingNumber);

    List<ShipmentDto> getShipmentsByOrderId(Long orderId);

    List<ShipmentDto> getShipmentsByCustomerId(Long customerId);

    ShipmentDto createShipment(CreateShipmentRequest request);

    ShipmentDto updateShipmentStatus(Long id, UpdateShipmentStatusRequest request);

    ShipmentDto updateShipmentStatus(Long id, ShipmentStatus status, String description);

    void deleteShipment(Long id);
}