package com.vinsguru.cockroachdb.controller;

import com.vinsguru.cockroachdb.entity.Product;
import com.vinsguru.cockroachdb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public void createProduct(@RequestBody Product product){
        this.productService.createProduct(product);
    }

    @GetMapping("/all")
    public Mono<List<Product>> getAllProducts(){
        return Mono.fromSupplier(() -> this.productService.getAllProducts());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable final String id){
        this.productService.deleteProduct(UUID.fromString(id));
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody final Product product){
       return this.productService.updateProduct(product);
    }

}
