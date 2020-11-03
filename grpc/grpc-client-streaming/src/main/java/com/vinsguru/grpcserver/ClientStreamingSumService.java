package com.vinsguru.grpcserver;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import io.grpc.stub.StreamObserver;

public class ClientStreamingSumService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public StreamObserver<Input> sumAll(StreamObserver<Output> responseObserver) {
        return new InputStreamObserver(responseObserver);
    }

}
