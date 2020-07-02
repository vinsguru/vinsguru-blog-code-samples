package com.vinsguru.grpc.service;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.Input;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AggregatorService {

    @GrpcClient("calculator-service")
    private CalculatorServiceGrpc.CalculatorServiceBlockingStub blockingStub;

    public Flux<Long> getAllDoubles(final int number){
        //  build input object
        Input input = Input.newBuilder()
                .setNumber(number)
                .build();

        return Flux.create(fluxSink -> {
            this.blockingStub.getAllDoubles(input)
                    .forEachRemaining(output -> fluxSink.next(output.getResult()));
            fluxSink.complete();
        });
    }

}
