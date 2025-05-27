package com.alten.shop.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.shop.products.models.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
