package com.fit.shippingservice.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fit.shippingservice.dto.CreateShipmentRequest;
import com.fit.shippingservice.dto.ShipmentDto;
import com.fit.shippingservice.entity.Shipment;
import com.fit.shippingservice.entity.ShipmentStatus;

@Component
public class ShipmentMapper {

    public ShipmentDto toDto(Shipment shipment) {
        if (shipment == null) {
            return null;
        }

        return new ShipmentDto(
                shipment.getId(),
                shipment.getOrderId(),
                shipment.getCustomerId(),
                shipment.getTrackingNumber(),
                shipment.getCarrierName(),
                shipment.getShippingMethod(),
                shipment.getOriginAddress(),
                shipment.getDestinationAddress(),
                shipment.getStatus(),
                shipment.getCreatedAt(),
                shipment.getUpdatedAt(),
                shipment.getEstimatedDeliveryDate(),
                shipment.getActualDeliveryDate(),
                shipment.getNotes());
    }

    public Shipment createEntityFromRequest(CreateShipmentRequest request) {
        if (request == null) {
            return null;
        }

        LocalDateTime now = LocalDateTime.now();

        Shipment shipment = new Shipment();
        shipment.setOrderId(request.getOrderId());
        shipment.setCustomerId(request.getCustomerId());
        shipment.setTrackingNumber(generateTrackingNumber());
        shipment.setCarrierName(request.getCarrierName());
        shipment.setShippingMethod(request.getShippingMethod());
        shipment.setOriginAddress(request.getOriginAddress());
        shipment.setDestinationAddress(request.getDestinationAddress());
        shipment.setStatus(ShipmentStatus.PENDING);
        shipment.setCreatedAt(now);
        shipment.setUpdatedAt(now);
        shipment.setEstimatedDeliveryDate(request.getEstimatedDeliveryDate());
        shipment.setNotes(request.getNotes());

        return shipment;
    }

    private String generateTrackingNumber() {
        // Generate a unique tracking number
        return "SHIP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}