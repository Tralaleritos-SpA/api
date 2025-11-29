package com.tralaleritos.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tralaleritos.api.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    // MÉTODO NUEVO: Para la vista "Mis Pedidos"
    List<Order> findByUserIdOrderByCreatedAtDesc(UUID userId);
}