package com.alten.shop.wishlist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alten.shop.commons.dtos.CommonResponse;
import com.alten.shop.products.models.entities.Product;
import com.alten.shop.products.services.ProductService;
import com.alten.shop.users.models.entities.User;
import com.alten.shop.users.services.UserService;
import com.alten.shop.wishlist.exceptions.WishlistErrors;
import com.alten.shop.wishlist.exceptions.WishlistException;
import com.alten.shop.wishlist.models.dtos.WishlistResponse;
import com.alten.shop.wishlist.models.entities.Wishlist;
import com.alten.shop.wishlist.models.entities.WishlistItem;
import com.alten.shop.wishlist.repositories.WishlistItemRepository;
import com.alten.shop.wishlist.repositories.WishlistRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistItemRepository wishlistItemRepository;
    private final UserService userService;
    private final ProductService productService;
    
    @Override
    public List<WishlistResponse> getWishlist() {
        User user = userService.getAuthenticatedUser();
        Wishlist wishlist = wishlistRepository.findByUser(user).orElseThrow(() -> new WishlistException(WishlistErrors.WISHLIST_NOT_FOUND));

        return wishlistItemRepository.findByWishlist(wishlist).stream()
                .map(wishlistItem -> {
                    return WishlistResponse.builder().productName(wishlistItem.getProduct().getName())
                    .productPrice(wishlistItem.getProduct().getPrice())
                            .build();
                })
                .toList();
    }

    @Override
    public CommonResponse addToWishlist(Long productId) {
        User user = userService.getAuthenticatedUser();

        Wishlist wishlist = wishlistRepository.findByUser(user).orElseThrow(() -> new WishlistException(WishlistErrors.WISHLIST_NOT_FOUND));

        Product product = productService.getProductById(productId);

        Optional<WishlistItem> wishlistItem = wishlistItemRepository.findByWishlistAndProduct(wishlist,product);

        if(wishlistItem.isPresent()) throw new WishlistException(WishlistErrors.ALREADY_IN_WISHLIST);

        WishlistItem item = WishlistItem.builder().product(product).wishlist(wishlist).build();

        wishlistItemRepository.save(item);

        return CommonResponse.builder()
        .success(true)
        .build();

    }

    @Override
    public CommonResponse deleteFromWishlist(Long productId) {
        User user = userService.getAuthenticatedUser();

        Wishlist wishlist = wishlistRepository.findByUser(user).orElseThrow(() -> new WishlistException(WishlistErrors.WISHLIST_NOT_FOUND));

        Product product = productService.getProductById(productId);

        WishlistItem item = wishlistItemRepository.findByWishlistAndProduct(wishlist,product).orElseThrow(() -> new WishlistException(WishlistErrors.NOT_FOUND_IN_WISHLIST));

        wishlistItemRepository.delete(item);

         return CommonResponse.builder()
                .success(true)
                .build();

    }

}
