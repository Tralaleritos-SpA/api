package com.tralaleritos.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tralaleritos.api.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    List<Role> findByActiveTrue();
}