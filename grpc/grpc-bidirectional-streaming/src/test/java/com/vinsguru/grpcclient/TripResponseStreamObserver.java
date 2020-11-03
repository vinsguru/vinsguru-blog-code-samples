package com.vinsguru.grpcclient;

import com.google.common.util.concurrent.Uninterruptibles;
import com.vinsguru.gps.TripRequest;
import com.vinsguru.gps.TripResponse;
import io.grpc.stub.StreamObserver;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TripResponseStreamObserver implements StreamObserver<TripResponse> {

    private StreamObserver<TripRequest> requestStreamObserver;

    @Override
    public void onNext(TripResponse tripResponse) {
       if(tripResponse.getRemainingDistance() > 0){
           print(tripResponse);
           this.drive();
       }else{
           this.requestStreamObserver.onCompleted();
       }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        System.out.println("Trip Completed");
    }

    public void startTrip(StreamObserver<TripRequest> requestStreamObserver){
        this.requestStreamObserver = requestStreamObserver;
        this.drive();
    }

    private void drive(){
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        TripRequest tripRequest = TripRequest.newBuilder().setDistanceTravelled(ThreadLocalRandom.current().nextInt(1, 10)).build();
        requestStreamObserver.onNext(tripRequest);
    }

    private void print(TripResponse tripResponse){
        System.out.println(LocalTime.now() + ": Remaining Distance : " + tripResponse.getRemainingDistance());
        System.out.println(LocalTime.now() + ": Time To Reach (sec): " + tripResponse.getTimeToDestination());
        System.out.println("------------------------------");
    }

}
