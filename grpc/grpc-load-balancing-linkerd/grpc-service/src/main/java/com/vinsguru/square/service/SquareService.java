package com.vinsguru.square.service;


import com.vinsguru.model.Input;
import com.vinsguru.model.Output;
import com.vinsguru.model.SquareServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.net.InetAddress;
import java.net.UnknownHostException;


@GrpcService
public class SquareService extends SquareServiceGrpc.SquareServiceImplBase {

    @Override
    public void findSquareUnary(Input request, StreamObserver<Output> responseObserver) {
        var number = request.getNumber();
        Output output = Output.newBuilder()
                .setNumber(number)
                .setResult(number * number)
                .setHost(getHostName())
                .build();
        responseObserver.onNext(output);
        responseObserver.onCompleted();
    }

    private String getHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

}
