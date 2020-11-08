package com.vinsguru.producer.service;

import com.vinsguru.dto.Category;
import com.vinsguru.dto.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class ProductRepository {

    private static final List<Product> PRODUCTS = List.of(
            // appliances
            new Product("oven", 500.00, Category.APPLIANCES),
            new Product("dishwasher", 125.00, Category.APPLIANCES),
            new Product("heater", 65.00, Category.APPLIANCES),
            new Product("vacuum cleaner", 48.00, Category.APPLIANCES),
            new Product("refrigerator", 1200.00, Category.APPLIANCES),
            // books
            new Product("how to win friends and influence", 13.00, Category.BOOKS),
            new Product("ds and algorithms", 70.00, Category.BOOKS),
            new Product("effective java", 41.00, Category.BOOKS),
            new Product("clean architecture", 32.00, Category.BOOKS),
            new Product("microservices", 16.00, Category.BOOKS),
            // cosmetics
            new Product("brush", 9.50, Category.COSMETICS),
            new Product("face wash", 13.00, Category.COSMETICS),
            new Product("makeup mirror", 17.50, Category.COSMETICS),
            // electronics
            new Product("sony 4k tv", 999.25, Category.ELECTRONICS),
            new Product("headphone", 133.25, Category.ELECTRONICS),
            new Product("macbook", 2517.25, Category.ELECTRONICS),
            new Product("speaker", 65.25, Category.ELECTRONICS),
            // outdoor
            new Product("plants", 9.75, Category.OUTDOOR),
            new Product("power tools", 73.50, Category.OUTDOOR),
            new Product("pools", 111.75, Category.OUTDOOR)
    );

    public Product getRandomProduct(){
        int random = ThreadLocalRandom.current().nextInt(0, 20);
        return PRODUCTS.get(random);
    }

}
