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
    private final UserService userService;
    private final ProductService productService;
    
    @Override
    public List<WishlistResponse> getWishlist() {
        User user = userService.getAuthenticatedUser();
        return wishlistRepository.findByUser(user).stream()
                .map(wishlist -> {
                    return WishlistResponse.builder().productName(wishlist.getProduct().getName())
                    .productPrice(wishlist.getProduct().getPrice())
                            .build();
                })
                .toList();
    }

    @Override
    public CommonResponse addToWishlist(Long productId) {
        User user = userService.getAuthenticatedUser();

        Product product = productService.getProductById(productId);

        Optional<Wishlist> item = wishlistRepository.findByUserAndProduct(user,product);

        if(item.isPresent()) throw new WishlistException(WishlistErrors.ALREADY_IN_WISHLIST);

        Wishlist wishlist = Wishlist.builder().product(product).user(user).build();

        wishlistRepository.save(wishlist);

        return CommonResponse.builder()
        .success(true)
        .build();

    }

    @Override
    public CommonResponse deleteFromWishlist(Long productId) {
        User user = userService.getAuthenticatedUser();

        Product product = productService.getProductById(productId);

        Wishlist item = wishlistRepository.findByUserAndProduct(user,product).orElseThrow(() -> new WishlistException(WishlistErrors.NOT_FOUND_IN_WISHLIST));

        wishlistRepository.delete(item);

         return CommonResponse.builder()
                .success(true)
                .build();

    }

}
