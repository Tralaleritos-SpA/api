package com.tralaleritos.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tralaleritos.api.model.Category;
import com.tralaleritos.api.service.CategoryService;

@RequestMapping("/api/v1/categories")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getBrands() {
        List<Category> categories = categoryService.findAllCategories();

        if (!categories.isEmpty()) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Category>> getActiveCategories() {
        List<Category> categories = categoryService.findActiveCategories();

        if (!categories.isEmpty()) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id) {
        Optional<Category> brandOptional = categoryService.findCategoryById(id);

        if (brandOptional.isPresent()) {

            Category category = brandOptional.get();
            return new ResponseEntity<>(category, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        if (category.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Category savedCategory = categoryService.saveCategory(category);

        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody Category categoryDetails) {

        if (!id.equals(categoryDetails.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Category updatedCategory = categoryService.updateCategory(categoryDetails);

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBrand(@PathVariable UUID id) {
        categoryService.deleteCategory(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
