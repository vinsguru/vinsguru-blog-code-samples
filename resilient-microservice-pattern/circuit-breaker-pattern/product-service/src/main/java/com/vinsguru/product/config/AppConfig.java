package com.vinsguru.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Bean
    public RestClient restClient(@Value("${rating.service.endpoint}") String baseUrl) {
        return RestClient.builder()
                         .baseUrl(baseUrl)
                         .build();
    }

    @Bean
    public ExecutorService executorService(){
        return Executors.newVirtualThreadPerTaskExecutor();
    }

}
