package com.vinsguru.redismasterslave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RedisController {

    private static final String KEY = "VINSGURU";

    @Autowired
    private StringRedisTemplate template;

    @GetMapping("/{name}")
    public void addToSet(@PathVariable String name) {
        this.template.opsForSet().add(KEY, name);
    }

    @GetMapping("/get")
    public Set<String> getKeyValues() {
        return this.template.opsForSet().members(KEY);
    }

}
