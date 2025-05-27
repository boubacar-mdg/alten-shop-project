package com.alten.shop.products.controllers;

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

import com.alten.shop.commons.dtos.CommonResponse;
import com.alten.shop.products.models.dtos.ProductRequest;
import com.alten.shop.products.models.dtos.ProductResponse;
import com.alten.shop.products.services.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/products")
@Tag(name = "Produits")
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Liste des produits")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
    public ResponseEntity<List<ProductResponse>> getProductList(){

                List<ProductResponse> products = productService.getProductList();

        return ResponseEntity.ok(products);
    }

    @PostMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Créer un nouveau produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class)),
            }),
    })
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class)),
            }) ProductRequest productRequest) throws UnsupportedEncodingException {

        ProductResponse productResponse = productService.createProduct(productRequest);

        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Détails d'un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class)),
            }),
    })
    public ResponseEntity<ProductResponse> getProductDetails( @PathVariable Long id) throws UnsupportedEncodingException {

        ProductResponse response = productService.getProductResponseById(id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Mettre à jour un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class)),
            }),
    })
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class)),
            }) ProductRequest productRequest,  @PathVariable Long id) throws UnsupportedEncodingException {

        ProductResponse response = productService.updateProduct(id, productRequest);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Supprimer un produit")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })

    public ResponseEntity<CommonResponse> deleteProduct(
              @PathVariable Long id) {

        CommonResponse response = productService.deleteProduct(id);

        return ResponseEntity.ok(response);
    }
}
