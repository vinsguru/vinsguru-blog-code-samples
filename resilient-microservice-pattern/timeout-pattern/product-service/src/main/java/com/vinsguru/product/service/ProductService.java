package com.vinsguru.product.service;

import com.vinsguru.dto.ProductDto;
import com.vinsguru.product.entity.Product;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@AllArgsConstructor
public class ProductService {

    private final RatingServiceClient ratingServiceClient;
    private final ExecutorService executorService;

    // assume this would be DB in real life
    private Map<Integer, Product> db;

    @PostConstruct
    private void init(){
        this.db = Map.of(
                1, Product.of(1, "Blood On The Dance Floor", 12.45),
                2, Product.of(2, "The Eminem Show", 12.12)
        );
    }

    public CompletableFuture<ProductDto> getProductDto(int productId){
        // assuming this is a DB call
        var product = CompletableFuture.supplyAsync(() -> this.db.get(productId), executorService);
        var rating = this.ratingServiceClient.getProductRatingDto(productId);
        return product.thenCombine(rating, (p, r) -> ProductDto.of(productId, p.getDescription(), p.getPrice(), r));
    }

}
