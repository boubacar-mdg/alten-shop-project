package com.alten.shop.products.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alten.shop.commons.dtos.CommonResponse;
import com.alten.shop.products.exceptions.ProductErrors;
import com.alten.shop.products.exceptions.ProductException;
import com.alten.shop.products.models.dtos.ProductRequest;
import com.alten.shop.products.models.dtos.ProductResponse;
import com.alten.shop.products.models.entities.Product;
import com.alten.shop.products.repositories.ProductRepository;
import com.alten.shop.utils.Tools;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) throws UnsupportedEncodingException {
        isUserAuthorized();
        Product product = modelMapper.map(productRequest, Product.class);
        product.setCreatedAt(System.currentTimeMillis() / 1000);
        product.setUpdatedAt(System.currentTimeMillis() / 1000);
        product.setInternalReference(Tools.generateReference(10));
        productRepository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) throws UnsupportedEncodingException {
        isUserAuthorized();
        Product existingProduct = getProductById(id);
        Product product = modelMapper.map(productRequest, Product.class);
        product.setId(existingProduct.getId());
        product.setCreatedAt(existingProduct.getCreatedAt());
        product.setUpdatedAt(System.currentTimeMillis() / 1000);
        productRepository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getProductList() {
        return productRepository.findAll().stream()
                .map((data) -> modelMapper.map(data, ProductResponse.class))
                .toList();
    }

    @Override
    public CommonResponse deleteProduct(Long id) {
        isUserAuthorized();
        Product product = getProductById(id);
        productRepository.delete(product);
        return CommonResponse.builder().success(true).build();
    }

    @Override
    public ProductResponse getProductResponseById(Long id){
        return modelMapper.map(getProductById(id), ProductResponse.class);
    }

    private Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ProductException(ProductErrors.PRODUCT_NOT_FOUND));
    }

    private void isUserAuthorized(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        if(!email.equals("admin@admin.com")) throw new ProductException(ProductErrors.UNAUTHORIZED_PRODUCT_MANAGEMENT);
    }


}
