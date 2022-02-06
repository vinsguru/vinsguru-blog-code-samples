package com.vinsguru.cacheaside.service.impl;

import com.vinsguru.cacheaside.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
public class ProductServiceWithRedisCache extends ProductServiceWithNoCache {

    private static final String KEY = "product";

    @Autowired
    private ReactiveHashOperations<String, Integer, ProductDto> hashOperations;

    @Override
    public Mono<ProductDto> getProduct(Integer id) {
        return hashOperations.get(KEY, id)
                             .switchIfEmpty(this.getFromDatabaseAndCache(id));
    }

    @Override
    public Mono<Void> updateProduct(Integer id, Mono<ProductDto> mono) {
        return super.updateProduct(id, mono)
                                  .then(this.hashOperations.remove(KEY, id))
                                  .then();
    }

    private Mono<ProductDto> getFromDatabaseAndCache(Integer id) {
        return super.getProduct(id)
                    .flatMap(dto -> this.hashOperations.put(KEY, id, dto)
                                                       .thenReturn(dto));
    }

}
