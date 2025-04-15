package com.fit.productservice.service;

import com.fit.productservice.dto.request.RequestAddCategoryDTO;
import com.fit.productservice.entity.Category;
import com.fit.productservice.mapper.CategoryMapper;
import com.fit.productservice.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public List<Category> getAllCategories() {
        log.info("Fetching all categories");
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        log.info("Fetching category with id: {}", id);
        return categoryRepository.findById(id).orElse(null);
    }

    public Category createCategory(RequestAddCategoryDTO categoryDTO) {
        return categoryRepository.save(categoryMapper.toCategory(categoryDTO));
    }

    public Category updateCategory(Long id, Category category) {
        log.info("Updating category with id: {}", id);
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(category.getName());
                    return categoryRepository.save(existingCategory);
                })
                .orElse(null);
    }

    public void deleteCategory(Long id) {
        log.info("Deleting category with id: {}", id);
        categoryRepository.deleteById(id);
    }
}
