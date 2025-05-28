package com.alten.shop.cart.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.shop.cart.models.dtos.CartResponse;
import com.alten.shop.cart.services.CartService;
import com.alten.shop.commons.dtos.CommonResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/cart")
@Tag(name = "Panier")
public class CartController {

        private final CartService cartService;

        @GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Liste les éléments du panier")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
        public ResponseEntity<List<CartResponse>> getProductList() {

                List<CartResponse> cartResponses = cartService.getCartItems();

                return ResponseEntity.ok(cartResponses);
        }

        @PostMapping(path = "/{productId}", produces = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Ajouter le produit au panier ou ajouter une unité")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
        public ResponseEntity<CommonResponse> addProductToCart(@PathVariable Long productId) {

                CommonResponse commonResponse = cartService.addCartItem(productId);

                return ResponseEntity.ok(commonResponse);
        }

        @PatchMapping(path = "/{productId}/remove-one", produces = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Supprimer une unité du produit dans le panier")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
        public ResponseEntity<CommonResponse> removeOneQuantity(@PathVariable Long productId) {

                CommonResponse commonResponse = cartService.removeOneFromQuantity(productId);

                return ResponseEntity.ok(commonResponse);
        }

        @DeleteMapping(path = "/{productId}", produces = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Supprimer le produit du panier")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
        public ResponseEntity<CommonResponse> deleteProduct(@PathVariable Long productId) {

                CommonResponse commonResponse = cartService.deleteCartItem(productId);

                return ResponseEntity.ok(commonResponse);
        }

}
