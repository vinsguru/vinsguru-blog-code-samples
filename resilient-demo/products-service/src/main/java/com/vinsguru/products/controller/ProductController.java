package com.vinsguru.products.controller;

import com.vinsguru.dto.ProductDTO;
import com.vinsguru.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public ProductDTO getProduct(@PathVariable int id){
        return this.productService.getProduct(id);
    }

    @GetMapping("/products")
    public List<ProductDTO> getProducts(){
        return this.productService.getProducts();
    }

}
