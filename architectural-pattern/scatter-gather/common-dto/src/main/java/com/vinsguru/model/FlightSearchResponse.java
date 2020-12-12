package com.vinsguru.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class FlightSearchResponse {

    private FlightSearchRequest searchRequest;
    private List<FlightSchedule> schedules;

}
