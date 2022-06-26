package com.vinsguru.webfluxscattergather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

    private String type;
    private Double discount;
    private LocalDate endDate;

}
