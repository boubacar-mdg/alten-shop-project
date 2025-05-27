package com.alten.shop.wishlist.services;

import java.util.List;

import com.alten.shop.commons.dtos.CommonResponse;
import com.alten.shop.wishlist.models.dtos.WishlistResponse;

public interface WishlistService{
    public List<WishlistResponse> getWishlist();
    public CommonResponse addToWishlist(Long productId);
    public CommonResponse deleteFromWishlist(Long productId);
}
