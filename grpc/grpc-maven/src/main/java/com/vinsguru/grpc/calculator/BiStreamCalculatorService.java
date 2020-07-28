package com.vinsguru.grpc.calculator;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import io.grpc.stub.StreamObserver;

public class BiStreamCalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public StreamObserver<Input> playUpTo100(StreamObserver<Output> responseObserver) {
        // print host name
        HostnamePrinter.print();

        return new StreamObserver<>() {

            private int sum;

            @Override
            public void onNext(Input input) {
                sum = sum + input.getNumber();
                responseObserver.onNext(Output.newBuilder().setResult(sum).build());
                if(sum >= 100)
                    this.onCompleted();
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onCompleted();
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
