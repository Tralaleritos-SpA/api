package com.tralaleritos.api.DTO;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private UUID userId;
    private List<CartItemDTO> items; 
    private String fullName;
    private String phone;
    private String shippingAddress;
    private String shippingCity;
    private String shippingZip;
    private int shippingFee;
}