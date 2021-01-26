package com.vinsguru.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication
public class RSocketClientApplication implements CommandLineRunner {

    @Autowired
    private RSocketRequester rSocketRequester;

    public static void main(String[] args) {
        SpringApplication.run(RSocketClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Flux.range(1, 10000)
                .delayElements(Duration.ofMillis(100))
                .flatMap(i -> rSocketRequester.route("square-calculator").data(i).retrieveMono(Integer.class).retry(1))
                .doOnNext(i -> System.out.println("Response : " + i))
                .blockLast();
    }
}
