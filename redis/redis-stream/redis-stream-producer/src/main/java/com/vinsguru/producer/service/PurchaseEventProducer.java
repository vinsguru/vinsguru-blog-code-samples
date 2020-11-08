package com.vinsguru.producer.service;

import com.vinsguru.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PurchaseEventProducer {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Value("${stream.key}")
    private String streamKey;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ReactiveRedisTemplate<String, String> redisTemplate;

    @Scheduled(fixedRateString= "${publish.rate}")
    public void publishEvent(){
        Product product = this.repository.getRandomProduct();
        ObjectRecord<String, Product> record = StreamRecords.newRecord()
                .ofObject(product)
                .withStreamKey(streamKey);
        this.redisTemplate
                .opsForStream()
                .add(record)
                .subscribe(System.out::println);
        atomicInteger.incrementAndGet();
    }

    @Scheduled(fixedRate = 10000)
    public void showPublishedEventsSoFar(){
        System.out.println(
                "Total Events :: " + atomicInteger.get()
        );
    }

}
