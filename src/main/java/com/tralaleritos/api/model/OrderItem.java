package com.tralaleritos.api.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // <-- IMPORTAR ESTA LÍNEA

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem { //

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; //

    // Relación al pedido principal
    @ManyToOne
    @JoinColumn(name = "order_id")
    // CLAVE: Ignora las propiedades que crean el ciclo cuando OrderItem es serializado.
    // 'items' cierra el ciclo Order -> items -> OrderItem -> order
    // 'user' se añade por si la entidad User tiene también una referencia a Order.
    @JsonIgnoreProperties({"items", "user"}) 
    private Order order; //

    // Relación al producto
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; //
    
    @Column(nullable = false)
    private int quantity; //
    
    @Column(nullable = false)
    private int unitPrice; //
    
    @Column(nullable = false)
    private int subTotal; //
}