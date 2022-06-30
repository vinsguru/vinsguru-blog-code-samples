package com.vinsguru.grpc;

import com.vinsguru.grpc.io.FileUploadService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GRPCServer {

    private Server server;

    public void start(){
        try{
            // build gRPC server
            this.server = ServerBuilder.forPort(6565)
                                       .addService(new FileUploadService())
                                       .build();

            // start
            server.start();

            // shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("gRPC server is shutting down!");
                server.shutdown();
            }));

            server.awaitTermination();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stop(){
        this.server.shutdownNow();
    }

}
