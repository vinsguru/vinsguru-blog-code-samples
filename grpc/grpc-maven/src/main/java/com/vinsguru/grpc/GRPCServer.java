package com.vinsguru.grpc;

import com.vinsguru.grpc.calculator.UnaryCalculatorService;
import com.vinsguru.grpc.io.FileUploadService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GRPCServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        // build gRPC server
        Server server = ServerBuilder
                .forPort(6565)
                .addService(new UnaryCalculatorService())
                .addService(new FileUploadService())
             //   .addService(new StreamCalculatorService())
             //   .addService(new BiStreamCalculatorService())
                .build();

        // start
        server.start();

        // shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("gRPC server is shutting down!");
            server.shutdown();
        }));

        // wait for 1 hr
        server.awaitTermination(1, TimeUnit.HOURS);

    }

}
