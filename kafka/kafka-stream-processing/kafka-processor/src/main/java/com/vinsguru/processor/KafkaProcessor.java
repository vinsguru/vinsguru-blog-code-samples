package com.vinsguru.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Configuration
public class KafkaProcessor {

    /*
    *   consumer the numbers received via kafka topic
    *   Consumer<T> makes this as kafka consumer of T
    * */

    @Bean
    public Consumer<Flux<Integer>> squaredNumberConsumer(){
        return (flux) -> flux
                            .doOnNext(value -> System.out.println("Consumed : " + value))
                            .subscribe();
    };

}
