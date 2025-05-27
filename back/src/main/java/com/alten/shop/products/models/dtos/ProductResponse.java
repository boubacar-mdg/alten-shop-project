package com.alten.shop.products.models.dtos;

import java.math.BigDecimal;

import com.alten.shop.products.enums.InventoryStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private BigDecimal price;
    private int quantity;
    private String internalReference;
    private int shellId;
    private InventoryStatus inventoryStatus;
    private int rating;
}
