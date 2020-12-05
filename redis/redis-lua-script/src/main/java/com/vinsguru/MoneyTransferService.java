package com.vinsguru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyTransferService {

    @Autowired
    private RedisScript<Boolean> script;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void transfer(String fromAccount, String toAccount, int amount){
        this.redisTemplate
                .execute(script, List.of(fromAccount, toAccount), String.valueOf(amount));
    }

}
