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
        this.channel = ManagedChannelBuilder
                            .forAddress("vins.example.com", 80)
                            .idleTimeout(5000, TimeUnit.SECONDS)
                .usePlaintext()
                .build();
        this.clientStub = CalculatorServiceGrpc.newBlockingStub(channel);
    }

    @Test
    public void unaryServiceTest() throws InterruptedException {


        IntStream.range(1, 20000)
                .forEach(i -> {
                    Input input = Input.newBuilder()
                            .setNumber(25)
                            .build();
                    Output factorial = this.clientStub.findFactorial(input);
                    System.out.println(factorial.getResult());
                });

        /*
                      this.clientStub.findFactorial(input, new StreamObserver<Output>() {
                        @Override
                        public void onNext(Output output) {
                            //check the result
                            System.out.println("Output :: " + output.getResult());
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            System.out.println(throwable.getMessage());
                        }

                        @Override
                        public void onCompleted() {
                            System.out.println("Done");
                        }
                    });
         */


        Thread.sleep(50000);
    }

    @After
    public void teardown(){
        this.channel.shutdown();
    }

}
