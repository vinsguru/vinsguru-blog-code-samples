package com.vinsguru.kafkastream.processor;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OddNumberProcessor {

    @Value("${kafka.topic.odd-output}")
    private String oddOutput;

    public void process(KStream<String, Long> stream){
        stream
                .filter((k, v) -> v % 2 != 0)
                .mapValues(v -> {
                    System.out.println("Doubling Odd :: " + v);
                    return v * 2;
                })
                .to(oddOutput);
    }

}
