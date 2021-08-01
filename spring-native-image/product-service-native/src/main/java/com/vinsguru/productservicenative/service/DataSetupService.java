package com.vinsguru.productservicenative.service;

import com.vinsguru.productservicenative.entity.Product;
import com.vinsguru.productservicenative.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataSetupService implements CommandLineRunner {

    @Autowired
    private ProductRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Flux<Product> productFlux = Flux.range(1, 100)
                .map(i -> Product.create(i, "product " + i, ThreadLocalRandom.current().nextInt(1, 1000)))
                .flatMap(this.repository::save);

        this.repository.deleteAll()
                .thenMany(productFlux)
                .doFinally(s -> System.out.println("Data setup done : " + s))
                .subscribe();
    }

}
