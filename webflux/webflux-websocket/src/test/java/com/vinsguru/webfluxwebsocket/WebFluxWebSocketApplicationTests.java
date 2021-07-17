package com.vinsguru.webfluxwebsocket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.time.Duration;

@SpringBootTest
class WebFluxWebSocketApplicationTests {

	@Test
	void webSocketTest() {

		WebSocketClient client = new ReactorNettyWebSocketClient();
		URI uri = URI.create("ws://localhost:8080/uppercase");

		Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1));
		client.execute(uri, webSocketSession ->
				// send msg
				webSocketSession.send(
						longFlux.map(i -> webSocketSession.textMessage("vinsguru" + i))
				).and(
						// receive message
						webSocketSession.receive()
								.map(WebSocketMessage::getPayloadAsText)
								.doOnNext(System.out::println)
				).then()
		).block(Duration.ofSeconds(5));

	}

}
