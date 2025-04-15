package com.fit.shippingservice.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fit.shippingservice.dto.ShipmentTrackingDto;
import com.fit.shippingservice.dto.UpdateShipmentStatusRequest;
import com.fit.shippingservice.entity.Shipment;
import com.fit.shippingservice.entity.ShipmentTracking;

@Component
public class ShipmentTrackingMapper {

    public ShipmentTrackingDto toDto(ShipmentTracking tracking) {
        if (tracking == null) {
            return null;
        }

        return new ShipmentTrackingDto(
                tracking.getId(),
                tracking.getShipment().getId(),
                tracking.getStatus(),
                tracking.getLocation(),
                tracking.getDescription(),
                tracking.getTimestamp(),
                tracking.getUpdatedBy());
    }

    public ShipmentTracking createEntityFromRequest(UpdateShipmentStatusRequest request, Shipment shipment) {
        if (request == null || shipment == null) {
            return null;
        }

        ShipmentTracking tracking = new ShipmentTracking();
        tracking.setShipment(shipment);
        tracking.setStatus(request.getStatus());
        tracking.setLocation(request.getLocation());
        tracking.setDescription(request.getDescription());
        tracking.setTimestamp(LocalDateTime.now());
        tracking.setUpdatedBy(request.getUpdatedBy() != null ? request.getUpdatedBy() : "SYSTEM");

        return tracking;
    }
}