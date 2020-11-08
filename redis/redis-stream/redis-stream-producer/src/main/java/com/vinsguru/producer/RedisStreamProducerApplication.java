package com.vinsguru.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RedisStreamProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisStreamProducerApplication.class, args);
    }

}
