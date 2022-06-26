package com.vinsguru.grpcclient;

import com.vinsguru.calculator.ReactorCalculatorServiceGrpc;
import com.vinsguru.calculator.Request;
import com.vinsguru.calculator.Response;
import com.vinsguru.grpcserver.CalculatorServer;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReactiveGrpcTest {

    private static final CalculatorServer SERVER = new CalculatorServer();
    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
    private static ReactorCalculatorServiceGrpc.ReactorCalculatorServiceStub serviceStub;

    @BeforeAll
    public static void setup() {
        EXECUTOR.execute(SERVER::start);
        var channel = ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        serviceStub = ReactorCalculatorServiceGrpc.newReactorStub(channel);
    }

    @Test
    public void successTest(){
        Request request = Request.newBuilder()
                             .setNumber(5)
                             .build();
        serviceStub.findSquare(request)
                   .map(Response::getResult)
                   .as(StepVerifier::create)
                   .expectNext(25)
                   .verifyComplete();
    }

    @Test
    public void failureTest(){
        Request request = Request.newBuilder()
                                 .setNumber(22)
                                 .build();

        serviceStub.findSquare(request)
                   .map(Response::getResult)
                   .as(StepVerifier::create)
                   .expectErrorMessage("FAILED_PRECONDITION: Not between 2 and 20")
                   .verify();
    }

    @AfterAll
    public static void stop(){
       SERVER.stop();
    }

}
