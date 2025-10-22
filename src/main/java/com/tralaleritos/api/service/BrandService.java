package com.tralaleritos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tralaleritos.api.exception.ResourceNotFoundException;
import com.tralaleritos.api.model.Brand;
import com.tralaleritos.api.repository.BrandRepository;

@Service
@Transactional
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }

    public List<Brand> findActiveBrands() {
        return brandRepository.findByActiveTrue();
    }

    public Optional<Brand> findBrandById(UUID id) {
        return brandRepository.findById(id);
    }

    public Brand updateBrand(Brand brand) {

        if (brand.getId() == null || !brandRepository.existsById(brand.getId())) {
            throw new ResourceNotFoundException("Brand with ID " + brand.getId() + " not found. Update failed.");
        }

        return brandRepository.save(brand);
    }

    public void deleteBrand(UUID id) {

        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException("Brand with ID " + id + " not found. Delete failed.");
        }

        Brand deactivatedBrand = brandRepository.findById(id).get();
        deactivatedBrand.setActive(false);

        brandRepository.save(deactivatedBrand);
    }
}
