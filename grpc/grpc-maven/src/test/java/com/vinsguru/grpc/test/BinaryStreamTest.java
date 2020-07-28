package com.vinsguru.grpc.test;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinaryStreamTest {

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
        StreamObserver<Input> inputStreamObserver = this.clientStub.withWaitForReady().playUpTo100(new StreamObserver<Output>() {
            @Override
            public void onNext(Output output) {
                System.out.println("Server sum : " + output.getResult());
            }

            @Override
            public void onError(Throwable throwable) {
                // error
            }

            @Override
            public void onCompleted() {
                System.out.println("Client done with requests");
            }
        });

        for (int i = 1; i <= 30; i++) {
            inputStreamObserver.onNext(Input.newBuilder().setNumber(i).build());
        }
        inputStreamObserver.onCompleted();

        Thread.sleep(5000);


    }

    @After
    public void teardown(){
        this.channel.shutdown();
    }

}
