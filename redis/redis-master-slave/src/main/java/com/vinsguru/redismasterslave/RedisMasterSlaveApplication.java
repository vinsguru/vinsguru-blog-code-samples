package com.vinsguru.redismasterslave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisMasterSlaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisMasterSlaveApplication.class, args);
	}

}
