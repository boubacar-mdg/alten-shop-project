package com.alten.shop.wishlist.models.entities;

import com.alten.shop.products.models.entities.Product;
import com.alten.shop.users.models.entities.User;

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
@Table(name = "WISHLIST")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WISHLIST")
    @SequenceGenerator(name = "SEQ_WISHLIST", sequenceName = "SEQ_WISHLIST", allocationSize = 20, initialValue = 10)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
}
