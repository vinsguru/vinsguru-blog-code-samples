package com.vinsguru.reactor.grpc;

import com.vinsguru.reactor.grpc.calculator.CalculatorService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GRPCServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        // build gRPC server
        Server server = ServerBuilder
                .forPort(6565)
                .addService(new CalculatorService())
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
