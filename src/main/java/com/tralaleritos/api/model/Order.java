package com.tralaleritos.api.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int total_price;

    @Column(name = "created_at", nullable = false) // Mapeo a la columna real de la DB
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(nullable = false, length = 50)
    private String status = "PENDING";
    
    // --- CAMPOS DE ENVÍO Y CONTACTO AÑADIDOS ---
    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 100)
    private String shippingAddress;

    @Column(nullable = false, length = 50)
    private String shippingCity;

    @Column(nullable = false, length = 10)
    private String shippingZip;
    // ----------------------------------------
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    @Column(nullable = false)
    private int shippingFee;
}