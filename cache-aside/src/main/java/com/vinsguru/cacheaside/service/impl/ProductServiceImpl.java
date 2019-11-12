package com.vinsguru.cacheaside.service.impl;

import com.vinsguru.cacheaside.dto.ProductDto;
import com.vinsguru.cacheaside.entity.Product;
import com.vinsguru.cacheaside.repository.ProductRepository;
import com.vinsguru.cacheaside.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<ProductDto> getProduct(long id) {
        return this.productRepository
                .findById(id)
                .map(this::entityToDto);
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        final Product product = this.dtoToEntity(productDto);
        this.productRepository
                .findById(productDto.getId())
                .ifPresent(p -> this.productRepository.save(product));
    }

    private ProductDto entityToDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantityAvailable(product.getQtyAvailable());
        return dto;
    }

    private Product dtoToEntity(ProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQtyAvailable(dto.getQuantityAvailable());
        return product;
    }
}
