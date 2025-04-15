package com.fit.shippingservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fit.shippingservice.common.exception.ResourceNotFoundException;
import com.fit.shippingservice.dto.ShipmentTrackingDto;
import com.fit.shippingservice.entity.Shipment;
import com.fit.shippingservice.entity.ShipmentStatus;
import com.fit.shippingservice.entity.ShipmentTracking;
import com.fit.shippingservice.mapper.ShipmentTrackingMapper;
import com.fit.shippingservice.repository.ShipmentRepository;
import com.fit.shippingservice.repository.ShipmentTrackingRepository;
import com.fit.shippingservice.service.ShipmentTrackingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentTrackingServiceImpl implements ShipmentTrackingService {

    private final ShipmentTrackingRepository trackingRepository;
    private final ShipmentRepository shipmentRepository;
    private final ShipmentTrackingMapper trackingMapper;

    @Override
    public List<ShipmentTrackingDto> getTrackingHistoryByShipmentId(Long shipmentId) {
        shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with id: " + shipmentId));

        return trackingRepository.findByShipmentIdOrderByTimestampDesc(shipmentId).stream()
                .map(trackingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShipmentTrackingDto addTrackingUpdate(Shipment shipment, ShipmentStatus status,
            String location, String description, String updatedBy) {

        ShipmentTracking tracking = new ShipmentTracking();
        tracking.setShipment(shipment);
        tracking.setStatus(status);
        tracking.setLocation(location);
        tracking.setDescription(description);
        tracking.setTimestamp(LocalDateTime.now());
        tracking.setUpdatedBy(updatedBy != null ? updatedBy : "SYSTEM");

        tracking = trackingRepository.save(tracking);

        return trackingMapper.toDto(tracking);
    }

    @Override
    public ShipmentTrackingDto getLatestTrackingUpdate(Long shipmentId) {
        shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with id: " + shipmentId));

        List<ShipmentTracking> trackingList = trackingRepository.findByShipmentIdOrderByTimestampDesc(shipmentId);

        if (trackingList.isEmpty()) {
            throw new ResourceNotFoundException("No tracking updates found for shipment id: " + shipmentId);
        }

        return trackingMapper.toDto(trackingList.get(0));
    }
}