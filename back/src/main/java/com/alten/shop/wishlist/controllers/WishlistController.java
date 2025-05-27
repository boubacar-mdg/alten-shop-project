package com.alten.shop.wishlist.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.shop.commons.dtos.CommonResponse;
import com.alten.shop.wishlist.models.dtos.WishlistResponse;
import com.alten.shop.wishlist.services.WishlistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/wishlist")
@Tag(name = "List d'envie")
public class WishlistController {

        private final WishlistService wishlistService;

        @GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Liste les éléments de ma liste d'envie")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
        public ResponseEntity<List<WishlistResponse>> getWishlist() {

                List<WishlistResponse> wishlistResponses = wishlistService.getWishlist();

                return ResponseEntity.ok(wishlistResponses);
        }

        @PostMapping(path = "/{productId}/add", produces = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Ajouter un produit à ma liste d'envie")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
        public ResponseEntity<CommonResponse> addProductToWishlist(@PathVariable Long productId) {

                CommonResponse commonResponse = wishlistService.addToWishlist(productId);

                return ResponseEntity.ok(commonResponse);
        }
        
        @DeleteMapping(path = "/{productId}/delete", produces = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Supprimer le produit de ma liste d'envie")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
        public ResponseEntity<CommonResponse> deleteProduct(@PathVariable Long productId) {

                CommonResponse commonResponse = wishlistService.deleteFromWishlist(productId);

                return ResponseEntity.ok(commonResponse);
        }

}
