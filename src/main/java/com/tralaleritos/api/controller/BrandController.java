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

import com.tralaleritos.api.model.Brand;
import com.tralaleritos.api.service.BrandService;

@RequestMapping("/api/v1/brands")
@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> getBrands() {
        List<Brand> brands = brandService.findAllBrands();

        if (!brands.isEmpty()) {
            return new ResponseEntity<>(brands, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Brand>> getActiveBrands() {
        List<Brand> brands = brandService.findActiveBrands();

        if (!brands.isEmpty()) {
            return new ResponseEntity<>(brands, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable UUID id) {
        Optional<Brand> brandOptional = brandService.findBrandById(id);

        if (brandOptional.isPresent()) {

            Brand brand = brandOptional.get();
            return new ResponseEntity<>(brand, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        if (brand.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Brand savedBrand = brandService.saveBrand(brand);

        return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable UUID id, @RequestBody Brand brandDetails) {

        if (!id.equals(brandDetails.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Brand updatedBrand = brandService.updateBrand(brandDetails);

        return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBrand(@PathVariable UUID id) {
        brandService.deleteBrand(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
