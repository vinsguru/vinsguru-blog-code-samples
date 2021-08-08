package com.vinsguru.reactive.r2dbc.service;

import com.vinsguru.reactive.r2dbc.entity.Product;
import com.vinsguru.reactive.r2dbc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Mono<Page<Product>> getProducts(PageRequest pageRequest){
        return this.repository.findAllBy(pageRequest.withSort(Sort.by("price").descending()))
                        .collectList()
                        .zipWith(this.repository.count())
                        .map(t -> new PageImpl<>(t.getT1(), pageRequest, t.getT2()));
    }

}
