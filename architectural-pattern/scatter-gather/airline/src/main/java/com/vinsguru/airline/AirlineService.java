package com.vinsguru.airline;

import com.vinsguru.model.FlightSchedule;
import com.vinsguru.model.FlightSearchRequest;
import com.vinsguru.util.ObjectUtil;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AirlineService {

    private static final String AIRLINE = Objects.toString(System.getenv("AIRLINE_NAME"), "UNKNOWN");
    private static final String NATS_SERVER = Objects.toString(System.getenv("NATS_SERVER"), "nats://localhost:4222");

    public static void main(String[] args) throws IOException, InterruptedException {
        final Connection nats = Nats.connect(NATS_SERVER);
        final Dispatcher dispatcher = nats.createDispatcher(msg -> {});
        dispatcher.subscribe("flight.search", (msg) -> {

            ObjectUtil.toObject(msg.getData(), FlightSearchRequest.class)
                   .ifPresent(searchRequest -> {
                       List<FlightSchedule> flightSchedules = getFlightSchedules(searchRequest);
                       nats.publish(msg.getReplyTo(), ObjectUtil.toBytes(flightSchedules));
                   });
        });
    }

    private static List<FlightSchedule> getFlightSchedules(FlightSearchRequest searchRequest){
        // input parameter is not used
        int randomNoResponse = ThreadLocalRandom.current().nextInt(0, 3);
        return IntStream.rangeClosed(0, randomNoResponse)
                .mapToObj(i -> getRandomSchedule())
                .collect(Collectors.toList());
    }

    private static FlightSchedule getRandomSchedule(){
        int randomDate = ThreadLocalRandom.current().nextInt(0,  30);
        int randomPrice = ThreadLocalRandom.current().nextInt(50, 500);
        var date = LocalDate.now().plusDays(randomDate);
        return FlightSchedule.of(date.toString(), randomPrice, AIRLINE);
    }

}
