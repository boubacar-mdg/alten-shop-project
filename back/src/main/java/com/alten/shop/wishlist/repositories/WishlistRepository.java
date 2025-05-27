package com.alten.shop.wishlist.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.shop.products.models.entities.Product;
import com.alten.shop.users.models.entities.User;
import com.alten.shop.wishlist.models.entities.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUserAndProduct(User user, Product product);
    List<Wishlist> findByUser(User user);

}
