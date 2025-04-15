package com.fit.shippingservice.dto;

import java.time.LocalDateTime;

import com.fit.shippingservice.entity.ShipmentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentTrackingDto {
    private Long id;
    private Long shipmentId;
    private ShipmentStatus status;
    private String location;
    private String description;
    private LocalDateTime timestamp;
    private String updatedBy;
}