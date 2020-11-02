package com.vinsguru.grpcclient.nonblocking;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnaryServiceTest {

    private ManagedChannel channel;
    private CalculatorServiceGrpc.CalculatorServiceStub clientStub;

    @Before
    public void setup(){
        this.channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        this.clientStub = CalculatorServiceGrpc.newStub(channel);
    }

    @Test
    public void unaryServiceTest() throws InterruptedException {
        // build the request object
        Input input = Input.newBuilder()
                .setNumber(5)
                .build();

        // receive the response
        this.clientStub.findFactorial(input, new OutputStreamObserver());

        // block here for testing or count down latch
        Thread.sleep(3000);
    }

    @After
    public void teardown(){
        this.channel.shutdown();
    }

}
