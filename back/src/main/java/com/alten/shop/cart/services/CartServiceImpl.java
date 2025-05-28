package com.alten.shop.cart.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alten.shop.cart.exceptions.CartErrors;
import com.alten.shop.cart.exceptions.CartException;
import com.alten.shop.cart.models.dtos.CartResponse;
import com.alten.shop.cart.models.entities.Cart;
import com.alten.shop.cart.models.entities.CartItem;
import com.alten.shop.cart.repositories.CartItemRepository;
import com.alten.shop.cart.repositories.CartRepository;
import com.alten.shop.commons.dtos.CommonResponse;
import com.alten.shop.products.enums.InventoryStatus;
import com.alten.shop.products.exceptions.ProductErrors;
import com.alten.shop.products.exceptions.ProductException;
import com.alten.shop.products.models.entities.Product;
import com.alten.shop.products.services.ProductService;
import com.alten.shop.users.models.entities.User;
import com.alten.shop.users.services.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public List<CartResponse> getCartItems() {
        User user = userService.getAuthenticatedUser();
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new CartException(CartErrors.CART_NOT_FOUND));
        return cartItemRepository.findByCart(cart).stream()
                .map(cartItem -> {
                    return CartResponse.builder().productName(cartItem.getProduct().getName())
                            .productPrice(cartItem.getProduct().getPrice()).quantity(cartItem.getQuantity())
                            .totalPrice(
                                    cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity())))
                            .build();
                })
                .toList();
    }

    @Override
    public CommonResponse addCartItem(Long productId) {
        User user = userService.getAuthenticatedUser();
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new CartException(CartErrors.CART_NOT_FOUND));

        Product product = productService.getProductById(productId);

        if (product.getInventoryStatus().equals(InventoryStatus.OUTOFSTOCK))
            throw new ProductException(ProductErrors.OUTOFSTOCK);

        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQuantity = item.getQuantity() + 1;

            if (newQuantity > product.getQuantity())
                throw new ProductException(ProductErrors.UNSUFFICIENT_STOCK);

            item.setQuantity(newQuantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(1)
                    .build();
            cartItemRepository.save(newItem);
        }

        cartRepository.save(cart);

        return CommonResponse.builder()
                .success(true)
                .build();

    }

    @Override
    public CommonResponse removeOneFromQuantity(Long productId) {
        User user = userService.getAuthenticatedUser();
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new CartException(CartErrors.CART_NOT_FOUND));

        Product product = productService.getProductById(productId);

        if (product.getInventoryStatus().equals(InventoryStatus.OUTOFSTOCK))
            throw new ProductException(ProductErrors.OUTOFSTOCK);

        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();

            if(item.getQuantity() == 1) {
                return deleteCartItem(productId);
            }
            
            int newQuantity = item.getQuantity() - 1;

            if (newQuantity > product.getQuantity())
                throw new ProductException(ProductErrors.UNSUFFICIENT_STOCK);

            item.setQuantity(newQuantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(1)
                    .build();
            cartItemRepository.save(newItem);
        }

        cartRepository.save(cart);

        return CommonResponse.builder()
                .success(true)
                .build();

    }

    @Override
    public CommonResponse deleteCartItem(Long productId) {
        User user = userService.getAuthenticatedUser();
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new CartException(CartErrors.CART_NOT_FOUND));

        Product product = productService.getProductById(productId);

        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new CartException(CartErrors.CART_ITEM_NOT_FOUND));

        cartItemRepository.delete(cartItem);

        return CommonResponse.builder()
                .success(true)
                .build();
    }

}
