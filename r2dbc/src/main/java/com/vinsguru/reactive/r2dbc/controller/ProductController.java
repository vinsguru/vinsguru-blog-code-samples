package com.vinsguru.reactive.r2dbc.controller;

import com.vinsguru.reactive.r2dbc.entity.Product;
import com.vinsguru.reactive.r2dbc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Flux<Product> getAll(){
        return this.productService.getAllProducts();
    }

    @PostMapping("/save")
    public Mono<Product> upsertProduct(@RequestBody Product product){
        return Objects.isNull(product.getId()) ?
                                            this.productService.createProduct(product) :
                                            this.productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable int id){
        return this.productService.deleteProduct(id);
    }

}
