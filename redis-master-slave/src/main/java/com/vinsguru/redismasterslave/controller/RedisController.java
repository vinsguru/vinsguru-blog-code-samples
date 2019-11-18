package com.vinsguru.redismasterslave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URL;

@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate template;

    @GetMapping("/{name}")
    public void addLink(@PathVariable String name) {
        template.opsForSet().add("name", name);
    }
}
