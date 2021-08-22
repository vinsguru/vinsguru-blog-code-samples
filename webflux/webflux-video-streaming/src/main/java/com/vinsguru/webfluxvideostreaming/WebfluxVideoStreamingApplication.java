package com.vinsguru.webfluxvideostreaming;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;

@SpringBootApplication
public class WebfluxVideoStreamingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxVideoStreamingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		DefaultDataBufferFactory sharedInstance = DefaultDataBufferFactory.sharedInstance;



	}
}
