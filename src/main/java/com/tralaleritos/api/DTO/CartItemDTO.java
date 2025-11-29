package com.tralaleritos.api.DTO;

import java.util.UUID;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private UUID productId;
    private int quantity;
    @Column(nullable = false)
    private int shippingFee;
}