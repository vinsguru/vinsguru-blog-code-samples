package com.vinsguru.grpcclient;

import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import com.vinsguru.calculator.ReactorCalculatorServiceGrpc;
import com.vinsguru.grpcserver.CalculatorServer;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReactiveGrpcTest {

    private static final CalculatorServer SERVER = new CalculatorServer();
    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
    private static ReactorCalculatorServiceGrpc.ReactorCalculatorServiceStub serviceStub;

    @BeforeAll
    public static void setup() throws Exception {
        EXECUTOR.execute(SERVER::start);
        var channel = ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        serviceStub = ReactorCalculatorServiceGrpc.newReactorStub(channel);
    }

    @Test
    public void findSquareTest(){
        Input input = Input.newBuilder()
                .setNumber(5)
                .build();
        serviceStub.findSquare(input)
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
        serviceStub.findFactors(input)
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(2L, 4L, 5L, 10L)
                .verifyComplete();
    }

    @Test
    public void sumAllTest(){
        Flux<Input> inputFlux = Flux.range(1, 10)
                .map(i -> Input.newBuilder().setNumber(i).build());
        serviceStub.findSum(inputFlux)
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(55L)
                .verifyComplete();
    }

    @AfterAll
    public static void stop(){
       SERVER.stop();
    }

}
