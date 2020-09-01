package com.vinsguru.square.service;


import com.vinsguru.model.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@GrpcService
public class MyGrpcService extends CPUIOServiceGrpc.CPUIOServiceImplBase {

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void findSquareUnary(Input request, StreamObserver<Output> responseObserver) {
        var number = request.getNumber();
        Output output = Output.newBuilder()
                .setNumber(number)
                .setResult(number * number).build();
        responseObserver.onNext(output);
        responseObserver.onCompleted();
    }

    @Override
    public void findSquareStream(Input request, StreamObserver<Output> responseObserver) {
        Runnable runnable = () -> {
            for (int i = 1; i <= request.getNumber(); i++) {
                Output output = Output.newBuilder()
                        .setNumber(i)
                        .setResult(i * i).build();
                responseObserver.onNext(output);
            }
            responseObserver.onCompleted();
        };
        new Thread(runnable).start();
    }

    @Override
    public StreamObserver<Input> findSquareBiStream(StreamObserver<Output> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(Input input) {
                var number = input.getNumber();
                Output output = Output.newBuilder()
                        .setNumber(number)
                        .setResult(number * number).build();
                responseObserver.onNext(output);
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

    @Override
    public void readFile(ReadRequest request, StreamObserver<ReadResponse> responseObserver) {
        var fileName = request.getName();
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(this.resourceLoader.getResource("classpath:" + fileName).getInputStream()))){
            String line;
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            responseObserver.onNext(ReadResponse.newBuilder().setContent(sb.toString()).build());
        } catch (IOException e) {
            e.printStackTrace();
            responseObserver.onError(e);
        }finally {
            responseObserver.onCompleted();
        }
    }
}
