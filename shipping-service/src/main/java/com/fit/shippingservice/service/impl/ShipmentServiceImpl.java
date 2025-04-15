package com.fit.shippingservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fit.shippingservice.common.exception.ResourceNotFoundException;
import com.fit.shippingservice.dto.CreateShipmentRequest;
import com.fit.shippingservice.dto.ShipmentDto;
import com.fit.shippingservice.dto.UpdateShipmentStatusRequest;
import com.fit.shippingservice.entity.Shipment;
import com.fit.shippingservice.entity.ShipmentStatus;
import com.fit.shippingservice.mapper.ShipmentMapper;
import com.fit.shippingservice.repository.ShipmentRepository;
import com.fit.shippingservice.service.ShipmentService;
import com.fit.shippingservice.service.ShipmentTrackingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;
    private final ShipmentTrackingService trackingService;

    @Override
    public List<ShipmentDto> getAllShipments() {
        return shipmentRepository.findAll().stream()
                .map(shipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShipmentDto getShipmentById(Long id) {
        return shipmentRepository.findById(id)
                .map(shipmentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with id: " + id));
    }

    @Override
    public ShipmentDto getShipmentByTrackingNumber(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber)
                .map(shipmentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Shipment not found with tracking number: " + trackingNumber));
    }

    @Override
    public List<ShipmentDto> getShipmentsByOrderId(Long orderId) {
        return shipmentRepository.findByOrderId(orderId).stream()
                .map(shipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipmentDto> getShipmentsByCustomerId(Long customerId) {
        return shipmentRepository.findByCustomerId(customerId).stream()
                .map(shipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ShipmentDto createShipment(CreateShipmentRequest request) {
        Shipment shipment = shipmentMapper.createEntityFromRequest(request);

        shipment = shipmentRepository.save(shipment);

        // Add initial tracking entry
        trackingService.addTrackingUpdate(
                shipment,
                ShipmentStatus.PENDING,
                request.getOriginAddress(),
                "Shipment created",
                "SYSTEM");

        return shipmentMapper.toDto(shipment);
    }

    @Override
    @Transactional
    public ShipmentDto updateShipmentStatus(Long id, UpdateShipmentStatusRequest request) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with id: " + id));

        shipment.setStatus(request.getStatus());
        shipment.setUpdatedAt(LocalDateTime.now());

        // Set delivery date if status is DELIVERED
        if (request.getStatus() == ShipmentStatus.DELIVERED) {
            shipment.setActualDeliveryDate(LocalDateTime.now());
        }

        shipment = shipmentRepository.save(shipment);

        // Add tracking update
        trackingService.addTrackingUpdate(
                shipment,
                request.getStatus(),
                request.getLocation(),
                request.getDescription(),
                request.getUpdatedBy());

        return shipmentMapper.toDto(shipment);
    }

    @Override
    @Transactional
    public ShipmentDto updateShipmentStatus(Long id, ShipmentStatus status, String description) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with id: " + id));

        shipment.setStatus(status);
        shipment.setUpdatedAt(LocalDateTime.now());

        // Set delivery date if status is DELIVERED
        if (status == ShipmentStatus.DELIVERED) {
            shipment.setActualDeliveryDate(LocalDateTime.now());
        }

        shipment = shipmentRepository.save(shipment);

        // Add tracking update
        trackingService.addTrackingUpdate(
                shipment,
                status,
                null,
                description,
                "SYSTEM");

        return shipmentMapper.toDto(shipment);
    }

    @Override
    public void deleteShipment(Long id) {
        if (!shipmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Shipment not found with id: " + id);
        }
        shipmentRepository.deleteById(id);
    }
}