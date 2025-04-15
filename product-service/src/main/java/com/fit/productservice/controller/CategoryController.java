package com.fit.productservice.controller;

import com.fit.productservice.common.annotation.ApiMessage;
import com.fit.productservice.dto.request.RequestAddCategoryDTO;
import com.fit.productservice.entity.Category;
import com.fit.productservice.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    CategoryService categoryService;

    @GetMapping
    @ApiMessage("Getting all categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        log.info("Fetching all categories");
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    @ApiMessage("Getting category by id")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        log.info("Fetching category with id: {}", id);
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    @ApiMessage("Creating new category")
    public ResponseEntity<Category> createCategory(@RequestBody RequestAddCategoryDTO categoryDTO) throws Exception {
        log.info("Creating new category: {}", categoryDTO);
        Category category = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(201).body(category);
    }

    @PutMapping("/{id}")
    @ApiMessage("Updating category")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        log.info("Updating category with id: {}", id);
        Category updatedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Deleting category")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.info("Deleting category with id: {}", id);
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
