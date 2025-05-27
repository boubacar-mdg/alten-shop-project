package com.alten.shop.products.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.alten.shop.commons.dtos.CommonResponse;
import com.alten.shop.products.models.dtos.ProductRequest;
import com.alten.shop.products.models.dtos.ProductResponse;
import com.alten.shop.products.models.entities.Product;

public interface ProductService{
    public List<ProductResponse> getProductList();
    public ProductResponse createProduct(ProductRequest productRequest) throws UnsupportedEncodingException;
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) throws UnsupportedEncodingException;
    public ProductResponse getProductResponseById(Long id);
    public Product getProductById(Long id);
    public CommonResponse deleteProduct(Long id);
}
