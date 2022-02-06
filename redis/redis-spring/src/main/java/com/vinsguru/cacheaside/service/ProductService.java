package com.vinsguru.cacheaside.service;

import com.vinsguru.cacheaside.dto.ProductDto;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductDto> getProduct(Integer id);
    Mono<Void> updateProduct(Integer id, Mono<ProductDto> productDto);

}
