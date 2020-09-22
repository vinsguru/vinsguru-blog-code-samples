package com.vinsguru.api;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class AggregatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregatorServiceApplication.class, args);
    }

    @Bean
    public Connection nats(@Value("${nats.server}") String natsServer) throws IOException, InterruptedException {
        return Nats.connect(natsServer);
    }

}