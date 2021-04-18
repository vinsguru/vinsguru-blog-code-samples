package com.vinsguru.r2dbccallback.callbacks;

import com.vinsguru.r2dbccallback.entity.Product;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductAfterConvert implements AfterConvertCallback<Product> {

    private final double seasonalDiscount = 0.2d;

    @Override
    public Publisher<Product> onAfterConvert(Product product, SqlIdentifier sqlIdentifier) {
        double price = (product.getPrice() * (1-seasonalDiscount));
        System.out.println("Actual  : " + product.getPrice());
        System.out.println("Updated : " + price);
        product.setPrice(price);
        return Mono.just(product);
    }

}
