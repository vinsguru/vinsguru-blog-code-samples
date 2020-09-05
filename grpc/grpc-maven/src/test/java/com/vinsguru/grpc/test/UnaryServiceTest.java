package com.vinsguru.grpc.test;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class UnaryServiceTest {

    private ManagedChannel channel;
    private CalculatorServiceGrpc.CalculatorServiceBlockingStub clientStub;

    @Before
    public void setup(){
        this.channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        this.clientStub = CalculatorServiceGrpc.newBlockingStub(channel);
    }

    @Test
    public void unaryServiceTest(){
        Input input = Input.newBuilder()
                .setNumber(5)
                .build();
        Output output = this.clientStub.findFactorial(input);

        //check the result
        Assert.assertEquals(120, output.getResult());
    }

    @After
    public void teardown(){
        this.channel.shutdown();
    }

}
