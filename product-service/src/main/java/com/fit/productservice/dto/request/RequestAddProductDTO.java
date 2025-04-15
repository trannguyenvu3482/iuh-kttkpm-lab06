package com.fit.productservice.dto.request;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.fit.productservice.entity.Product}
 */
@Value
public class RequestAddProductDTO implements Serializable {
    String name;
    String description;
    BigDecimal price;
    Long categoryId;
    String thumbnailUrl;
    Integer stockQuantity;
}