package com.vinsguru.cacheaside.config;

import com.vinsguru.cacheaside.dto.ProductDto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
public class RedisConfig {

    @Bean
    public ReactiveHashOperations<String, Integer, ProductDto> hashOperations(ReactiveRedisConnectionFactory redisConnectionFactory){
        var template = new ReactiveRedisTemplate<>(
                redisConnectionFactory,
                RedisSerializationContext.<String, ProductDto>newSerializationContext(new StringRedisSerializer())
                                         .hashKey(new GenericToStringSerializer<>(Integer.class))
                                         .hashValue(new Jackson2JsonRedisSerializer<>(ProductDto.class))
                                         .build()
        );
        return template.opsForHash();
    }


}
