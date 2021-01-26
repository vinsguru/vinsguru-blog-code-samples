package com.vinsguru.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@ConfigurationProperties(prefix = "rsocket.square-service")
public class DummyServiceRegistry {

    private List<RSocketServerInstance> servers;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public void setServers(List<RSocketServerInstance> servers) {
        this.servers = servers;
    }

    // we continuously switch b/2 instances every time
    public List<RSocketServerInstance> getServers() {
        atomicInteger.getAndIncrement();
        return IntStream.rangeClosed(0, 2)
                        .filter(i -> atomicInteger.get() % 3 != i)
                        .mapToObj(servers::get)
                        .collect(Collectors.toList());
    }

}
