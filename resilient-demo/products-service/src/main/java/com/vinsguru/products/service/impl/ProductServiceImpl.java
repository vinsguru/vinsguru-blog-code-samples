package com.vinsguru.products.service.impl;

import com.vinsguru.dto.ProductDTO;
import com.vinsguru.dto.ProductRatingDTO;
import com.vinsguru.products.service.ProductService;
import com.vinsguru.products.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private Map<Integer, ProductDTO> map;

    @Autowired
    private RatingService ratingService;

    @PostConstruct
    private void init(){
        this.map = new HashMap<>();
        this.map.put(1, this.getProduct(1, "The eminem show", 12.12));
        this.map.put(2, this.getProduct(2, "Blood on the dance floor", 12.45));
    }

    @Override
    public ProductDTO getProduct(int productId) {
        ProductDTO dto = this.map.get(productId);
        ProductRatingDTO productRating = this.ratingService.getRatings(productId);
        dto.setProductRating(productRating);
        return dto;
    }

    @Override
    public List<ProductDTO> getProducts() {
        return new ArrayList<>(this.map.values());
    }

    private ProductDTO getProduct(int productId, String description, double price){
        ProductDTO dto = new ProductDTO();
        dto.setProductId(productId);
        dto.setDescription(description);
        dto.setPrice(price);
        return dto;
    }
}
