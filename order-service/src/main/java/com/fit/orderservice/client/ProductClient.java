package com.fit.orderservice.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fit.orderservice.dto.response.ApiResponse;
import com.fit.orderservice.dto.response.ProductResponse;

@FeignClient(name = "product-service", url = "http://localhost:8080")
public interface ProductClient {
    @GetMapping("/api/products/{id}")
    ApiResponse<ProductResponse> getProductById(@PathVariable UUID id);
}