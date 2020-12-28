package com.vinsguru.rsocket.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class RSocketClientApplication implements CommandLineRunner {

    @Autowired
    private RSocketRequester rSocketRequester;

    public static void main(String[] args) {
        SpringApplication.run(RSocketClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 5; i++) {
            rSocketRequester.route("place-request") // message endpoint
                    .data("some request") // data
                    .retrieveMono(UUID.class) // request #
                    .subscribe(s -> System.out.println(LocalDateTime.now() + " :: Request # " + s));
        }

    }
}
