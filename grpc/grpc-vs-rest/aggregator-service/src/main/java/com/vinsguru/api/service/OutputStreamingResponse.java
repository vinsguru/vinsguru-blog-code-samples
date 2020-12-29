package com.vinsguru.api.service;

import com.vinsguru.model.Output;
import io.grpc.stub.StreamObserver;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class OutputStreamingResponse implements StreamObserver<Output> {

    private final Map<Integer, Integer> map;
    private final CompletableFuture<Map<Integer, Integer>> completableFuture;

    public OutputStreamingResponse(Map<Integer, Integer> map, CompletableFuture<Map<Integer, Integer>> completableFuture) {
        this.map = map;
        this.completableFuture = completableFuture;
    }

    @Override
    public void onNext(Output output) {
        this.map.put(output.getNumber(), output.getResult());
    }

    @Override
    public void onError(Throwable throwable) {
        completableFuture.cancel(true);
    }

    @Override
    public void onCompleted() {
        completableFuture.complete(this.map);
    }
}
