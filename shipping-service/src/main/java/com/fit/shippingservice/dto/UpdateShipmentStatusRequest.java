package com.fit.shippingservice.dto;

import com.fit.shippingservice.entity.ShipmentStatus;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShipmentStatusRequest {
    @NotNull(message = "Status is required")
    private ShipmentStatus status;

    private String location;

    @NotEmpty(message = "Description is required")
    private String description;

    private String updatedBy;
}