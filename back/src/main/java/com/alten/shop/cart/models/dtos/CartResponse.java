package com.alten.shop.cart.models.dtos;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal totalPrice;
    private int quantity;
}
