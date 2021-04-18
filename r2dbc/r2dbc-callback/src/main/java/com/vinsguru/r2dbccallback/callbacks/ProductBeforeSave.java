package com.vinsguru.r2dbccallback.callbacks;

import com.vinsguru.r2dbccallback.entity.Product;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.r2dbc.core.Parameter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductBeforeSave implements BeforeSaveCallback<Product> {

    @Override
    public Publisher<Product> onBeforeSave(Product product, OutboundRow outboundRow, SqlIdentifier sqlIdentifier) {
        outboundRow.put(SqlIdentifier.unquoted("created_by"), Parameter.from("vinsguru"));
        return Mono.just(product);
    }

}
