package com.vinsguru.webfluxscattergather.service;

import com.vinsguru.webfluxscattergather.client.ProductClient;
import com.vinsguru.webfluxscattergather.client.PromotionClient;
import com.vinsguru.webfluxscattergather.client.ReviewClient;
import com.vinsguru.webfluxscattergather.dto.Product;
import com.vinsguru.webfluxscattergather.dto.ProductAggregate;
import com.vinsguru.webfluxscattergather.dto.Promotion;
import com.vinsguru.webfluxscattergather.dto.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductAggregatorService {

    private final ProductClient productClient;
    private final PromotionClient promotionClient;
    private final ReviewClient reviewClient;

    public Mono<ProductAggregate> getProduct(Integer productId){
        return Mono.zip(
                this.productClient.getProduct(productId),
                this.promotionClient.getPromotion(productId),
                this.reviewClient.getReviews(productId)
        )
        .map(this::combine);
    }

    private ProductAggregate combine(Tuple3<Product, Promotion, List<Review>> tuple){
        return ProductAggregate.create(
                tuple.getT1(),
                tuple.getT2(),
                tuple.getT3()
        );
    }

}
