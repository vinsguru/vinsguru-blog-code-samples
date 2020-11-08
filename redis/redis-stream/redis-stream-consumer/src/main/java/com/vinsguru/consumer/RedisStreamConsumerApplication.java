package com.vinsguru.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RedisStreamConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisStreamConsumerApplication.class, args);
    }

}
