package com.fit.shippingservice.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateShipmentRequest {
    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    private String carrierName;
    private String shippingMethod;

    @NotEmpty(message = "Origin address is required")
    private String originAddress;

    @NotEmpty(message = "Destination address is required")
    private String destinationAddress;

    private LocalDateTime estimatedDeliveryDate;

    private String notes;
}