package com.vinsguru.grpc.test;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UnaryServiceTest {

    private ManagedChannel channel;
    private CalculatorServiceGrpc.CalculatorServiceBlockingStub clientStub;

    @Before
    public void setup() {
        this.channel = ManagedChannelBuilder
                        .forAddress("localhost", 6565)
                        .usePlaintext()
                        .enableRetry()
                        .build();
        this.clientStub = CalculatorServiceGrpc.newBlockingStub(channel);
    }

    @Test
    public void unaryServiceTest() {
        for (int i = 1; i <= 20; i++) {
            Input input = Input.newBuilder()
                    .setNumber(i)
                    .build();
            Output factorial = this.clientStub.findFactorial(input);
            System.out.println(factorial.getResult());
        }
    }

    @After
    public void teardown(){
        this.channel.shutdown();
    }

}
