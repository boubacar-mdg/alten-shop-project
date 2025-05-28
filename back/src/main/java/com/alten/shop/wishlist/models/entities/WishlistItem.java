package com.alten.shop.wishlist.models.entities;

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
@Table(name = "WISHLIST_ITEMS")
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WISHLIST_ITEMS")
    @SequenceGenerator(name = "SEQ_WISHLIST_ITEMS", sequenceName = "SEQ_WISHLIST_ITEMS", allocationSize = 20, initialValue = 10)
    private Long id;
    @ManyToOne
    private Wishlist wishlist;
    @ManyToOne
    private Product product;
}
