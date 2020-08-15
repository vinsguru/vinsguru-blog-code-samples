package com.vinsguru.rsocket;


import io.rsocket.core.RSocketServer;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.server.TcpServerTransport;
import reactor.core.Disposable;

public class Server {

    private static Disposable disposable;

    public static void start() {
        RSocketServer rSocketServer = RSocketServer.create();
        rSocketServer.acceptor(new SimpleRSocketAcceptor());
        rSocketServer.payloadDecoder(PayloadDecoder.ZERO_COPY);
        disposable = rSocketServer.bind(TcpServerTransport.create(6565))
                .subscribe();
    }

    public static void stop(){
        disposable.dispose();
    }

}
