package com.vinsguru.rating.client.dto;

import java.util.Arrays;

public enum Rating {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE;

    public static Rating getRating(int r){
        return Arrays.stream(Rating.values())
                .filter(rating -> rating.ordinal() == r - 1)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
