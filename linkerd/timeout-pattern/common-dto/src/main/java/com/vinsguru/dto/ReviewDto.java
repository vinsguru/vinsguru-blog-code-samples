package com.vinsguru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ReviewDto {

    private String userFirstname;
    private String userLastname;
    private int productId;
    private int rating;
    private String comment;

}
