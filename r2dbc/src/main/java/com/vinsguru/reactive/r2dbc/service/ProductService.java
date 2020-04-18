package com.vinsguru.reactive.r2dbc.service;

import com.vinsguru.reactive.r2dbc.entity.Product;
import com.vinsguru.reactive.r2dbc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Mono<Product> createProduct(final Product product){
        return this.repository
                    .save(product);
    }

    public Flux<Product> getAllProducts(){
        return this.repository
                .findAll();
    }

    @Transactional
    public Mono<Product> updateProduct(final Product product){
        return this.repository.findById(product.getId())
                .flatMap(p -> {
                    p.setDescription(product.getDescription());
                    p.setPrice(product.getPrice());
                    return this.repository.save(p);
                });
    }

    @Transactional
    public Mono<Void> deleteProduct(final int id){
        return this.repository.findById(id)
                .flatMap(this.repository::delete);
    }

}
