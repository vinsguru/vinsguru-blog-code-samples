package com.vinsguru.redisstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RedisStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisStreamApplication.class, args);
	}

}
