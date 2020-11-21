package com.vinsguru.grpcclient.blocking;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.ErrorResponse;
import com.vinsguru.calculator.Request;
import com.vinsguru.calculator.Response;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SquareServiceTest {

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
    public void squareServiceHappyPath(){
        // build the request object
        Request request = Request.newBuilder()
                .setNumber(50)
                .build();

        Response response = this.clientStub.findSquare(request);

        switch (response.getResponseCase()){
            case SUCCESS_RESPONSE:
                System.out.println("Success Response : " + response.getSuccessResponse().getResult());
                break;
            case ERROR_RESPONSE:
                System.out.println("Error Response : " + response.getErrorResponse().getErrorCode());
                break;
        }
    }

    @After
    public void teardown(){
        this.channel.shutdown();
    }

}
