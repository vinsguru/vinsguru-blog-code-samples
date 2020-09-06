package com.vinsguru.grpc.test;

import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import com.vinsguru.calculator.ReactorCalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class GRPCServiceTest {

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
    public void findFactorialTest(){
        Input input = Input.newBuilder()
                .setNumber(5)
                .build();
        this.serviceStub.findFactorial(Mono.just(input))
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(120L)
                .verifyComplete();
    }

    @Test
    public void getAllDoublesTest(){
        Input input = Input.newBuilder()
                .setNumber(5)
                .build();
        this.serviceStub.getAllDoubles(input)
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(2L, 4L, 6L, 8L, 10L)
                .verifyComplete();
    }

    @Test
    public void sumAllTest(){
        Flux<Input> inputFlux = Flux.range(1, 10)
                .map(i -> Input.newBuilder().setNumber(i).build());
        this.serviceStub.sumAll(inputFlux)
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(55L)
                .verifyComplete();
    }

    @Test
    public void playUpTo100Test(){
        Flux<Input> inputFlux = Flux.range(1, 30)
                .map(i -> Input.newBuilder().setNumber(i).build());
        this.serviceStub.playUpTo100(inputFlux)
                .map(Output::getResult)
                .doOnNext(o -> System.out.println("Server sum : " + o))
                .last()
                .as(StepVerifier::create)
                .expectNext(105L)
                .verifyComplete();
    }

}
