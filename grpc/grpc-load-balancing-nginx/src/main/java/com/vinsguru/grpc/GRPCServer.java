package com.vinsguru.grpc;

import com.vinsguru.grpc.calculator.UnaryCalculatorService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.util.concurrent.TimeUnit;

public class GRPCServer {

    public static void main(String[] args) throws Exception {

        Server server = ServerBuilder
                .forPort(6565)
                .addService(new UnaryCalculatorService())
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
