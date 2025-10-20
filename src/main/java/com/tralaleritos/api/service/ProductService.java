package com.tralaleritos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tralaleritos.api.exception.ResourceNotFoundException;
import com.tralaleritos.api.model.Product;
import com.tralaleritos.api.repository.ProductRepository;


@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    // CREATE: Save a new Product (ID will be generated)
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    // READ: Retrieve all Products (Existing method)
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    // READ: Retrieve a single Product by its UUID
    public Optional<Product> findProductById(UUID id){
        return productRepository.findById(id);
    }
    
    // UPDATE: Update a Product with existence check
    public Product updateProduct(Product product){

        // Checkea q exista el producto en la db
        if (product.getId() == null || !productRepository.existsById(product.getId())) {
            throw new ResourceNotFoundException("Product with ID " + product.getId() + " not found. Update failed.");
        }
        
        // Si existe, actualiza el objecto en la db
        return productRepository.save(product);
    }

    // DELETE: Delete a Product by its UUID
    public void deleteProduct(UUID id){

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product with ID " + id + " not found. Delete failed.");
        }
        productRepository.deleteById(id);
    }
}
