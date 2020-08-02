package com.vinsguru.test.rscoket;

import com.vinsguru.rsocket.Server;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class RSocketClient {

    private static RSocket rSocket;

    @BeforeClass
    public static void setUpClient(){
        Server.start();
        rSocket = RSocketConnector.connectWith(TcpClientTransport.create(6565))
                .block();
    }

    @Test
    public void fireAndForget(){
        this.getRequestPayload()
                .flatMap(payload -> rSocket.fireAndForget(payload))
                .blockLast(Duration.ofMinutes(1));
    }

    @Test
    public void requestAndResponse(){
        this.getRequestPayload()
                .flatMap(payload -> rSocket.requestResponse(payload))
                .doOnNext(response -> System.out.println("Response from server :: " + response.getDataUtf8()))
                .blockLast(Duration.ofMinutes(1));
    }

    @Test
    public void requestAndResponseStream(){
        this.getRequestPayload()
                .flatMap(payload -> rSocket.requestStream(payload))
                .doOnNext(response -> System.out.println("Response from server :: " + response.getDataUtf8()))
                .blockLast(Duration.ofMinutes(1));
    }

    @AfterClass
    public static void stopServer(){
        Server.stop();
    }

    private Flux<Payload> getRequestPayload(){
        return Flux.just("hi", "hello", "how", "are", "you")
                .delayElements(Duration.ofSeconds(1))
                .map(DefaultPayload::create);
    }

}
