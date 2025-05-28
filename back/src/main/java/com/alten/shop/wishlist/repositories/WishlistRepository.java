package com.alten.shop.wishlist.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.shop.users.models.entities.User;
import com.alten.shop.wishlist.models.entities.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUser(User user);

}
