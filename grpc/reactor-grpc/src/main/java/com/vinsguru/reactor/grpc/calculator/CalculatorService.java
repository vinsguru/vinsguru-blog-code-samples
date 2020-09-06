package com.vinsguru.reactor.grpc.calculator;

import com.vinsguru.calculator.Input;
import com.vinsguru.calculator.Output;
import com.vinsguru.calculator.ReactorCalculatorServiceGrpc;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

public class CalculatorService extends ReactorCalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public Mono<Output> findFactorial(Mono<Input> request) {
        return request
                .map(Input::getNumber)
                .map(this::factorial)
                .map(l -> Output.newBuilder().setResult(l).build());
    }

    @Override
    public Flux<Output> getAllDoubles(Mono<Input> request) {
        return request
                    .map(Input::getNumber)
                    .flatMapMany(i -> Flux.range(1, i))
                    .map(i -> i * 2)
                    .map(i -> Output.newBuilder().setResult(i).build());
    }

    @Override
    public Mono<Output> sumAll(Flux<Input> request) {
        return request
                    .map(Input::getNumber)
                    .reduce(Integer::sum)
                    .map(i -> Output.newBuilder().setResult(i).build());
    }

    @Override
    public Flux<Output> playUpTo100(Flux<Input> request) {
        AtomicInteger sum = new AtomicInteger(0);
        return request
                    .map(Input::getNumber)
                    .map(i -> {
                        sum.set(sum.get() + i);
                        System.out.println("Sum so far :: "+ sum.get());
                        return sum.get();
                    })
                    .map(i -> Output.newBuilder().setResult(i).build())
                    .handle((output, outputSynchronousSink) -> {
                        outputSynchronousSink.next(output);
                        if(output.getResult() > 100)
                            outputSynchronousSink.complete();
                    });
    }

    private long factorial(int number){
        if(number == 0)
            return 1;
        return number * factorial(number - 1);
    }

}
