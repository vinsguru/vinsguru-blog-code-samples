package com.vinsguru.grpcclient.blocking;

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
        // build the request object
        Input input = Input.newBuilder()
                .setNumber(5)
                .build();

        // receive the response
        Output output = this.clientStub.findFactorial(input);

        //check the result
        Assert.assertEquals(120, output.getResult());
    }

    @After
    public void teardown(){
        this.channel.shutdown();
    }

}
