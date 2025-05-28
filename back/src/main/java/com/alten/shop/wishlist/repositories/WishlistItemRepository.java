package com.alten.shop.wishlist.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.shop.products.models.entities.Product;
import com.alten.shop.wishlist.models.entities.Wishlist;
import com.alten.shop.wishlist.models.entities.WishlistItem;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByWishlist(Wishlist wishlist);
    Optional<WishlistItem> findByWishlistAndProduct(Wishlist wishlist, Product product);

}
