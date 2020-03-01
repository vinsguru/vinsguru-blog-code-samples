package com.vinsguru.products.service;

import com.vinsguru.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO getProduct(int productId);
    List<ProductDTO> getProducts();
}
