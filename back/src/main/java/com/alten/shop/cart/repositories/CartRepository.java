package com.alten.shop.cart.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.shop.cart.models.entities.Cart;
import com.alten.shop.users.models.entities.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
