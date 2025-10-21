package com.tralaleritos.api.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false, length = 200, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private int price;

    @Column(nullable = true)
    private String img_url;

    @Column(nullable = true, length = 400, unique = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // los productos accesorios tienen cantidad
    // ej: protectores x tienen -> 100 unidades
    @Column(nullable = true)
    private int quantity;

    // los juegos de mesa tienen cantidad minima y maxima de jugadores
    @Column(nullable = true)
    private int min_player_number;

    @Column(nullable = true)
    private int max_player_number;
}
