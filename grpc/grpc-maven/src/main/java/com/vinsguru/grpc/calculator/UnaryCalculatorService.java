package com.vinsguru.grpc.calculator;

import com.vinsguru.calculator.CalculatorServiceGrpc;
import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import io.grpc.stub.StreamObserver;

public class UnaryCalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void findFactorial(Input request, StreamObserver<Output> responseObserver) {
        var input = request.getNumber();

        // print host name
        HostnamePrinter.print();

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