package com.vinsguru.cacheaside.controller;

import com.vinsguru.cacheaside.dto.ProductDto;
import com.vinsguru.cacheaside.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long id){
        return this.productService.getProduct(id)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.noContent().build());
    }

}
