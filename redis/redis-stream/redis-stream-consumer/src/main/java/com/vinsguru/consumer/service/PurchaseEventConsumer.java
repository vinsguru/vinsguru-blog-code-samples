package com.vinsguru.consumer.service;

import com.vinsguru.dto.Product;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PurchaseEventConsumer implements StreamListener<String, ObjectRecord<String, Product>> {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Autowired
    private ReactiveRedisTemplate<String, String> redisTemplate;

    @Override
    @SneakyThrows
    public void onMessage(ObjectRecord<String, Product> record) {
        System.out.println(
                InetAddress.getLocalHost().getHostName() + " - consumed :" +
                        record.getValue()
        );
        this.redisTemplate
                .opsForZSet()
                .incrementScore("revenue", record.getValue().getCategory().toString(), record.getValue().getPrice())
                .subscribe();
        atomicInteger.incrementAndGet();
    }

    @Scheduled(fixedRate = 10000)
    public void showPublishedEventsSoFar(){
        System.out.println(
                "Total Consumed :: " + atomicInteger.get()
        );
    }

}
