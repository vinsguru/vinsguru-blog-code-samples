package com.vinsguru.client.config;

import io.rsocket.loadbalance.LoadbalanceTarget;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class LoadBalanceTargetConfig {

    @Autowired
    private DummyServiceRegistry serviceRegistry;

    @Bean
    public Flux<List<LoadbalanceTarget>> targets(){
        return Mono.fromSupplier(() -> serviceRegistry.getServers())
                .repeatWhen(longFlux -> longFlux.delayElements(Duration.ofSeconds(2)))
                .map(this::toLoadBalanceTarget);
    }

    private List<LoadbalanceTarget> toLoadBalanceTarget(List<RSocketServerInstance> rSocketServers){
        return rSocketServers.stream()
                .map(server -> LoadbalanceTarget.from(server.getHost() + server.getPort(), TcpClientTransport.create(server.getHost(), server.getPort())))
                .collect(Collectors.toList());
    }

}
