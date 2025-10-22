package com.tralaleritos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tralaleritos.api.exception.ResourceNotFoundException;
import com.tralaleritos.api.model.Category;
import com.tralaleritos.api.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> findActiveCategories() {
        return categoryRepository.findByActiveTrue();
    }

    public Optional<Category> findCategoryById(UUID id) {
        return categoryRepository.findById(id);
    }

    public Category updateCategory(Category category) {

        if (category.getId() == null || !categoryRepository.existsById(category.getId())) {
            throw new ResourceNotFoundException("Category with ID " + category.getId() + " not found. Update failed.");
        }

        return categoryRepository.save(category);
    }

    public void deleteCategory(UUID id) {

        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category with ID " + id + " not found. Delete failed.");
        }

        Category deactivatedCategory = categoryRepository.findById(id).get();
        deactivatedCategory.setActive(false);

        categoryRepository.save(deactivatedCategory);
    }
}
