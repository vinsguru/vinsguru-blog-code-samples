package com.vinsguru;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.script.RedisScript;

@Configuration
public class ScriptConfig {

    @Bean
    public RedisScript<Boolean> script() {
        Resource scriptSource = new ClassPathResource("scripts/moneyTransfer.lua");
        return RedisScript.of(scriptSource, Boolean.class);
    }

}
