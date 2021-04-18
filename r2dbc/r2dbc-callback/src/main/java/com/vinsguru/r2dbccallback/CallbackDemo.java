package com.vinsguru.r2dbccallback;

import com.vinsguru.r2dbccallback.entity.Product;
import com.vinsguru.r2dbccallback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class CallbackDemo implements CommandLineRunner {

    @Value("classpath:init.sql")
    private Resource initSql;

    @Autowired
    private R2dbcEntityTemplate entityTemplate;

    @Autowired
    private ProductRepository repository;

    @Override
    public void run(String... args) throws Exception {
        createTable()
                .then(insertRecordWithSpecialCharsDemo().then())
                .thenMany(this.repository.findAll())
                .subscribe(System.out::println);
    }

    private Mono<Void> createTable() throws IOException {
        String query = StreamUtils.copyToString(initSql.getInputStream(), StandardCharsets.UTF_8);
        System.out.println(query);
        return this.entityTemplate
                .getDatabaseClient()
                .sql(query)
                .then();
    }

    private Mono<Product> insertRecordWithSpecialCharsDemo(){
        String desc = "new- -pro/d&&u#c%t";
        Product product = new Product(desc, 10.0);
        return this.repository.save(product);
    }

}
