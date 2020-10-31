package com.vinsguru.reactivemongo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@ToString
public class Movie {

    @Id
    private String id;
    private String title;
    private int year;
    private String genre;
    private double imdbRating;

}
