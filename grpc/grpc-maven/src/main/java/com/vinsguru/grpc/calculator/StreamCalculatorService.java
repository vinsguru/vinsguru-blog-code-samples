package com.vinsguru.grpc.calculator;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import io.grpc.stub.StreamObserver;

import java.util.stream.IntStream;

public class StreamCalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    // server sends multiples data one by one
    // input is 6, server will send 2, 4, 6, 8, 10, 12
    @Override
    public void getAllDoubles(Input request, StreamObserver<Output> responseObserver) {
        // print host name
        HostnamePrinter.print();

        int index  = request.getNumber();
        IntStream.rangeClosed(1, index)
                .map(i -> i * 2)
                .mapToObj(i -> Output.newBuilder().setResult(i).build())
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Input> sumAll(StreamObserver<Output> responseObserver) {
        return new StreamObserver<Input>() {
            // initial sum = 0
            private int sum;

            @Override
            public void onNext(Input input) {
                sum = sum + input.getNumber();
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onCompleted();
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(Output.newBuilder().setResult(sum).build());
                responseObserver.onCompleted();
            }
        };
    }
}
