package com.vinsguru.client.config;

import io.rsocket.loadbalance.LoadbalanceTarget;
import io.rsocket.loadbalance.RoundRobinLoadbalanceStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Flux;

import java.util.List;

@Configuration
public class RSocketConfig {

    @Bean
    public RSocketStrategies rSocketStrategies() {
        return RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2CborEncoder()))
                .decoders(decoders -> decoders.add(new Jackson2CborDecoder()))
                .build();
    }

    @Bean
    public RSocketRequester rSocketClient(RSocketRequester.Builder builder, Flux<List<LoadbalanceTarget>> targetFlux){
       return builder.transports(targetFlux, new RoundRobinLoadbalanceStrategy());
    }

}
