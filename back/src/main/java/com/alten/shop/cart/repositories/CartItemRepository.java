package com.alten.shop.cart.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.shop.cart.models.entities.Cart;
import com.alten.shop.cart.models.entities.CartItem;
import com.alten.shop.products.models.entities.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

}
