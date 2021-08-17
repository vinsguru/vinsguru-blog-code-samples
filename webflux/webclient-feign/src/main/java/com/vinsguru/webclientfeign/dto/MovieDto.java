package com.vinsguru.webclientfeign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class MovieDto {

    private Integer id;
    private String title;
    private Integer year;
    private Double imdbRating;

}
