package com.vinsguru.reactive.r2dbc.repository;

import com.vinsguru.reactive.r2dbc.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveSortingRepository<Product, Integer> {
    Flux<Product> findAllBy(Pageable pageable);
}
