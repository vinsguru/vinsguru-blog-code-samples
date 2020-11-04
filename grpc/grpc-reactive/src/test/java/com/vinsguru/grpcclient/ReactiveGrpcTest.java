package com.vinsguru.grpcclient;

import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import com.vinsguru.calculator.ReactorCalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ReactiveGrpcTest {

    private ManagedChannel channel;
    private ReactorCalculatorServiceGrpc.ReactorCalculatorServiceStub serviceStub;

    @Before
    public void setup(){
        this.channel = ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        this.serviceStub = ReactorCalculatorServiceGrpc.newReactorStub(channel);
    }

    @Test
    public void findSquareTest(){
        Input input = Input.newBuilder()
                .setNumber(5)
                .build();
        this.serviceStub.findSquare(input)
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(25L)
                .verifyComplete();
    }

    @Test
    public void findFactorsTest(){
        Input input = Input.newBuilder()
                .setNumber(20)
                .build();
        this.serviceStub.findFactors(input)
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(2L, 4L, 5L, 10L)
                .verifyComplete();
    }

    @Test
    public void sumAllTest(){
        Flux<Input> inputFlux = Flux.range(1, 10)
                .map(i -> Input.newBuilder().setNumber(i).build());
        this.serviceStub.findSum(inputFlux)
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(55L)
                .verifyComplete();
    }

}
