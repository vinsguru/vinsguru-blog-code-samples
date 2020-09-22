package com.vinsguru.api.service;

import com.vinsguru.model.Input;
import com.vinsguru.model.Output;
import io.nats.client.Connection;
import io.nats.client.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Service
public class NatsSquareService {

    @Autowired
    private Connection nats;

    public Flux<Object> getSquareResponseUnary(int number){
        return Flux.range(1, number)
                .map(i -> Input.newBuilder().setNumber(i).build())
                .flatMap(i -> Mono.fromFuture(this.nats.request("nats.square.service", i.toByteArray()))
                                   .map(this::toOutput)
                                   .map(o -> (Object) Map.of(o.getNumber(), o.getResult())))

                .subscribeOn(Schedulers.boundedElastic());
    }

    private Output toOutput(final Message message) {
        Output o = null;
        try {
            o = Output.parseFrom(message.getData());
        }catch (Exception ignored){
        }
        return o;
    }

}
