package com.fit.productservice.controller;

import com.fit.productservice.common.annotation.ApiMessage;
import com.fit.productservice.dto.request.RequestAddProductDTO;
import com.fit.productservice.entity.Product;
import com.fit.productservice.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {
    ProductService productService;

    @GetMapping
    @ApiMessage("Getting all products")
    ResponseEntity<List<Product>> getAllProducts() {
        log.info("Fetching all products");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @ApiMessage("Getting product by id")
    ResponseEntity<Product> getProductById(@PathVariable String id) {
        log.info("Fetching product with id: {}", id);
        return ResponseEntity.ok(productService.getProductById(UUID.fromString(id)));
    }

    @PostMapping
    @ApiMessage("Creating new product")
    ResponseEntity<Product> createProduct(@RequestBody RequestAddProductDTO productDTO) throws Exception {
        log.info("Creating new product: {}", productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDTO));
    }

    @PutMapping("/{id}")
    @ApiMessage("Updating product")
    ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        log.info("Updating product with id: {}", id);
        return ResponseEntity.ok(productService.updateProduct(UUID.fromString(id), product));
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Deleting product")
    ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
