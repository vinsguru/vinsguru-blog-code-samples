package com.vinsguru.cockroachdb.service;

import com.vinsguru.cockroachdb.entity.Product;
import com.vinsguru.cockroachdb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // insert
    public void createProduct(Product product){
        UUID uuid = UUID.randomUUID();
        product.setId(uuid);
        this.productRepository.save(product);
    }

    // select all
    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    // delete
    @Transactional
    public void deleteProduct(UUID id){
        this.productRepository
                .findById(id)
                .ifPresent(this.productRepository::delete);
    }

    // update
    @Transactional
    public Product updateProduct(Product product){
        return this.productRepository
               .save(product);
    }

}
