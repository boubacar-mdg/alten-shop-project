package com.alten.shop.cart.services;

import java.util.List;

import com.alten.shop.cart.models.dtos.CartResponse;
import com.alten.shop.commons.dtos.CommonResponse;

public interface CartService{
    public List<CartResponse> getCartItems();
    public CommonResponse addCartItem(Long productId);
    public CommonResponse deleteCartItem(Long productId);
}
