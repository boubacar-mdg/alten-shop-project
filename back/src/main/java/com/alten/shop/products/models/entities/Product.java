package com.alten.shop.products.models.entities;

import java.math.BigDecimal;

import com.alten.shop.products.enums.InventoryStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCTS")
    @SequenceGenerator(name = "SEQ_PRODUCTS", sequenceName = "SEQ_PRODUCTS", allocationSize = 20, initialValue = 10)
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
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    private int rating;
    private Long createdAt;
    private Long updatedAt;
}
