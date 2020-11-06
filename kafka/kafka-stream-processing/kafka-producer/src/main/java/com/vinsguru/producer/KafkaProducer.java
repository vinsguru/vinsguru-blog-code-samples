package com.vinsguru.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Supplier;

@Configuration
public class KafkaProducer {

    /*
    *   produce a number from 1, every second
    *   Supplier<T> makes this as kafka producer of T
    * */

    @Bean
    public Supplier<Flux<Long>> numberProducer(){
        return () -> Flux.range(1, 1000)
                        .map(i -> (long) i)
                        .delayElements(Duration.ofSeconds(1));
    };

}
