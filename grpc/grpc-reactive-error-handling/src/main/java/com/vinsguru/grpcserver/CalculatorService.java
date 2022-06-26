package com.vinsguru.grpcserver;

import com.vinsguru.calculator.Request;
import com.vinsguru.calculator.Response;
import com.vinsguru.calculator.ReactorCalculatorServiceGrpc;
import io.grpc.Status;
import reactor.core.publisher.Mono;

public class CalculatorService extends ReactorCalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public Mono<Response> findSquare(Mono<Request> request) {
        return request
                .map(Request::getNumber)
                .doOnNext(this::doValidation)
                .map(i -> i * i)
                .map(i -> Response.newBuilder().setResult(i).build());
    }

    private void doValidation(int number){
        if(number < 2 || number > 20){
            Status status = Status.FAILED_PRECONDITION.withDescription("Not between 2 and 20");
            throw status.asRuntimeException();
        }
    }

}
