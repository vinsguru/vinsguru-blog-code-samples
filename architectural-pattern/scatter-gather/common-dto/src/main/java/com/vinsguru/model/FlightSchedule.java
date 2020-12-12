package com.vinsguru.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class FlightSchedule {

    private String date;
    private int price;
    private String airline;

}
