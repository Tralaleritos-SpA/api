package com.tralaleritos.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tralaleritos.api.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {

    List<Brand> findByActiveTrue();
}