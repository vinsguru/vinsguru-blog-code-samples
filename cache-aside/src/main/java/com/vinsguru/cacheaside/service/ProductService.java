package com.vinsguru.cacheaside.service;

import com.vinsguru.cacheaside.dto.ProductDto;

import java.util.Optional;

public interface ProductService {
    Optional<ProductDto> getProduct(long id);
    void updateProduct(ProductDto productDto);
}
