package com.vinsguru.reactive.r2dbc.repository;

import com.vinsguru.reactive.r2dbc.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
}
