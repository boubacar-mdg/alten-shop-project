package com.alten.shop.cart.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.shop.cart.models.dtos.CartResponse;
import com.alten.shop.cart.services.CartService;
import com.alten.shop.commons.dtos.CommonResponse;
import com.alten.shop.products.models.dtos.ProductRequest;
import com.alten.shop.products.models.dtos.ProductResponse;
import com.alten.shop.products.services.ProductService;

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

        @PostMapping(path = "/{productId}/add", produces = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Ajouter le produit au panier")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
        public ResponseEntity<CommonResponse> addProductToCart(@PathVariable Long productId) {

                CommonResponse commonResponse = cartService.addCartItem(productId);

                return ResponseEntity.ok(commonResponse);
        }
        
        @DeleteMapping(path = "/{productId}/delete", produces = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Supprimer le produit du panier")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
        public ResponseEntity<CommonResponse> deleteProduct(@PathVariable Long productId) {

                CommonResponse commonResponse = cartService.deleteCartItem(productId);

                return ResponseEntity.ok(commonResponse);
        }

}
