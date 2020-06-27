package com.vinsguru.grpc;

import com.vinsguru.grpc.service.BiStreamCalculatorService;
import com.vinsguru.grpc.service.StreamCalculatorService;
import com.vinsguru.grpc.service.UnaryCalculatorService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CalculatorServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        // build gRPC server
        Server server = ServerBuilder.forPort(6565)
                .addService(new UnaryCalculatorService())
                .addService(new StreamCalculatorService())
                .addService(new BiStreamCalculatorService())
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
