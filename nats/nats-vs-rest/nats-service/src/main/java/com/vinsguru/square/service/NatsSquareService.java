package com.vinsguru.square.service;


import com.google.protobuf.InvalidProtocolBufferException;
import com.vinsguru.model.Input;
import com.vinsguru.model.Output;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;

import java.io.IOException;
import java.util.Objects;

public class NatsSquareService {

    public static void main(String[] args) throws IOException, InterruptedException {
        String natsServer = Objects.toString(System.getenv("NATS_SERVER"), "nats://localhost:4222");
        Connection nats = Nats.connect(natsServer);
        Dispatcher dispatcher = nats.createDispatcher(msg -> {});
        dispatcher.subscribe("nats.square.service", (msg) -> {
            try {
                Input input = Input.parseFrom(msg.getData());
                Output output = Output.newBuilder().setNumber(input.getNumber()).setResult(input.getNumber() * input.getNumber()).build();
                nats.publish(msg.getReplyTo(), output.toByteArray());
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        });
    }

}
