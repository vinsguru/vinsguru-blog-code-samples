package com.vinsguru.rating.service;

import com.vinsguru.dto.ProductRatingDTO;

public interface RatingService {
    ProductRatingDTO getRatingForProduct(int productId);
}
