package com.fit.shippingservice.dto;

import java.time.LocalDateTime;

import com.fit.shippingservice.entity.ShipmentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDto {
    private Long id;
    private Long orderId;
    private Long customerId;

    private String trackingNumber;
    private String carrierName;
    private String shippingMethod;

    private String originAddress;
    private String destinationAddress;

    private ShipmentStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime estimatedDeliveryDate;
    private LocalDateTime actualDeliveryDate;

    private String notes;
}