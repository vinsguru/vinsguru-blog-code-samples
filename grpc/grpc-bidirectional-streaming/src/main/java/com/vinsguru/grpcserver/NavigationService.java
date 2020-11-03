package com.vinsguru.grpcserver;


import com.vinsguru.gps.NavigationServiceGrpc;
import com.vinsguru.gps.TripRequest;
import com.vinsguru.gps.TripResponse;
import io.grpc.stub.StreamObserver;

public class NavigationService extends NavigationServiceGrpc.NavigationServiceImplBase {

    @Override
    public StreamObserver<TripRequest> navigate(StreamObserver<TripResponse> responseObserver) {
        return new TripRequestObserver(responseObserver);
    }

}
