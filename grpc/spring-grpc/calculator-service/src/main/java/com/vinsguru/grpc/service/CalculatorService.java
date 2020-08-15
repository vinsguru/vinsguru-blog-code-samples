package com.vinsguru.grpc.service;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.stream.IntStream;

@GrpcService
public class CalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    // server sends multiples data one by one
    // input is 6, server will send 2, 4, 6, 8, 10, 12
    @Override
    public void getAllDoubles(Input request, StreamObserver<Output> responseObserver) {
        int index  = request.getNumber();
        IntStream.rangeClosed(1, index)
                .map(i -> i * 2) // add Thread.sleep to simulate time consuming operation
                .mapToObj(i -> Output.newBuilder().setResult(i).build())
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
