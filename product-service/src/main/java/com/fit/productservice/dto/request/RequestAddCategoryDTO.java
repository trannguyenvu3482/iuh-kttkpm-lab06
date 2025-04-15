package com.fit.productservice.dto.request;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fit.productservice.entity.Category}
 */
@Value
public class RequestAddCategoryDTO implements Serializable {
    String name;
    String description;
}