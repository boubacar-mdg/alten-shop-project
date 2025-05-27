package com.alten.shop.cart.models.entities;

import java.math.BigDecimal;

import com.alten.shop.products.models.entities.Product;

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
@Table(name = "CART_ITEMS")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CART_ITEMS")
    @SequenceGenerator(name = "SEQ_CART_ITEMS", sequenceName = "SEQ_CART_ITEMS", allocationSize = 20, initialValue = 10)
    private Long id;
    private int quantity;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;
}
