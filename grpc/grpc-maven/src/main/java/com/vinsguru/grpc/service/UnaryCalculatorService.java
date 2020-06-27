package com.vinsguru.grpc.service;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class UnaryCalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void findFactorial(Input request, StreamObserver<Output> responseObserver) {

        var input = request.getNumber();

        // if the input is below 0, raise an error event
        if(input < 0){
            responseObserver.onError(
                    Status.INVALID_ARGUMENT.augmentDescription("Input should be a positive int").asRuntimeException()
            );
            return;
        }

        // positive int
        long result = this.factorial(input);
        Output output = Output.newBuilder()
                .setResult(result)
                .build();
        responseObserver.onNext(output);
        responseObserver.onCompleted();

    }

    private long factorial(int number){
        if(number == 0)
            return 1;
        return number * factorial(number - 1);
    }

}