package com.vinsguru.redispubsub.consumer;

import com.vinsguru.redispubsub.producer.Joke;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber implements MessageListener {

    private RedisSerializer<Object> redisSerializer = new JdkSerializationRedisSerializer();

    @Override
    public void onMessage(Message message, byte[] bytes) {
        Joke joke = (Joke) redisSerializer.deserialize(message.getBody());
        System.out.println(
                joke
        );
    }
}
