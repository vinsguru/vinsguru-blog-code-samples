package com.vinsguru.products.service;

import com.vinsguru.dto.ProductRatingDTO;

public interface RatingService {
    ProductRatingDTO getRatings(int productId);
}
