package com.vinsguru.api.service;

import com.vinsguru.model.Input;
import com.vinsguru.model.SquareServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Service
public class GrpcSquareService {

    @GrpcClient("square")
    private SquareServiceGrpc.SquareServiceBlockingStub blockingStub;

    public Flux<Object> getSquareResponseUnary(int number){
        return Flux.range(1, number)
                .map(i -> Input.newBuilder().setNumber(i).build())
                .map(i -> this.blockingStub.findSquareUnary(i))
                .map(o -> (Object) Map.of(o.getNumber(), o.getResult()))
                .subscribeOn(Schedulers.boundedElastic());
    }

}
