package com.vinsguru.processor;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class KafkaProcessor {

    /*
    *   process the numbers received via kafka topic
    *   Function<T, R> makes this as kafka stream processor
    *   T is input type
    *   R is output type
    *
    * */

    @Bean
    public Function<KStream<String, Long>, KStream<String, Long>> evenNumberSquareProcessor(){
        return kStream ->  kStream
                            .filter((k, v) -> v % 2 == 0)
                            .peek((k, v) -> System.out.println("Squaring Even : " + v))
                            .mapValues(v -> v * v);
    };

}
