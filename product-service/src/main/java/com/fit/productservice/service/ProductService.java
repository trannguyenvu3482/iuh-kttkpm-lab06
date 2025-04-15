package com.fit.productservice.service;

import com.fit.productservice.dto.request.RequestAddProductDTO;
import com.fit.productservice.entity.Category;
import com.fit.productservice.entity.Product;
import com.fit.productservice.mapper.ProductMapper;
import com.fit.productservice.repository.CategoryRepository;
import com.fit.productservice.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;
    CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        log.info("Fetching product with id: {}", id);
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(RequestAddProductDTO productDTO) throws Exception {
        log.info("Creating new product: {}", productDTO);

        // Find category
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new Exception("Category not found"));

        Product product = productMapper.toProduct(productDTO);
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product updateProduct(UUID id, Product product) {
        log.info("Updating product with id: {}", id);
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    return productRepository.save(existingProduct);
                })
                .orElse(null);
    }

    public void deleteProduct(UUID id) {
        log.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }
}
