package com.fit.orderservice.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {
    private UUID id;
    private UUID productId;
    private Integer quantity;
    private BigDecimal price;
    private ProductResponse product;
}