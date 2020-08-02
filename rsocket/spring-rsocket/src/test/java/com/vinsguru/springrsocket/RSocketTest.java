package com.vinsguru.springrsocket;

import com.vinsguru.springrsocket.dto.MovieScene;
import com.vinsguru.springrsocket.dto.TicketRequest;
import com.vinsguru.springrsocket.dto.TicketStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

@SpringBootTest
public class RSocketTest {

    @Autowired
    private Mono<RSocketRequester> rSocketRequester;

    @Test
    public void ticketCancel(){
        Mono<Void> mono = this.rSocketRequester
                .map(r -> r.route("ticket.cancel").data(new TicketRequest(UUID.randomUUID())))
                .flatMap(RSocketRequester.RetrieveSpec::send);

        StepVerifier.create(mono)
                .verifyComplete();
    }


    @Test
    public void ticketPurchase(){
        Mono<TicketRequest> ticketRequestMono = this.rSocketRequester
                .map(r -> r.route("ticket.purchase").data(new TicketRequest(UUID.randomUUID())))
                .flatMap(r -> r.retrieveMono(TicketRequest.class))
                .doOnNext(r -> System.out.println(r.getRequestId() + ":" + r.getStatus()));
        StepVerifier.create(ticketRequestMono)
                .expectNextMatches(t -> t.getStatus().equals(TicketStatus.TICKET_ISSUED))
                .verifyComplete();
    }

    @Test
    public void playMovie(){
        Mono<TicketRequest> ticketRequestMono = this.rSocketRequester
                .map(r -> r.route("ticket.purchase").data(new TicketRequest(UUID.randomUUID())))
                .flatMap(r -> r.retrieveMono(TicketRequest.class));

        Flux<MovieScene> sceneFlux = this.rSocketRequester
                .zipWith(ticketRequestMono)
                .map(tuple -> tuple.getT1().route("movie.stream").data(tuple.getT2()))
                .flatMapMany(r -> r.retrieveFlux(MovieScene.class))
                .doOnNext(m -> System.out.println("Playing : " + m.getSceneDescription()));

        // assert all the movie scenes
        StepVerifier.create(sceneFlux)
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 1"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 2"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 3"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 4"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 5"))
                .verifyComplete();
    }

    @Test
    public void tvPlayMovie(){
        Flux<Integer> movieSceneFlux = Flux.just(1, 2, 2, 1, 2, 3, 3, 4, 5);
        Flux<MovieScene> tvFlux = this.rSocketRequester
                .map(r -> r.route("tv.movie").data(movieSceneFlux))
                .flatMapMany(r -> r.retrieveFlux(MovieScene.class))
                .doOnNext(m -> System.out.println("TV : " + m.getSceneDescription()));
        StepVerifier.create(tvFlux)
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 1"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 2"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 2"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 1"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 2"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 3"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 3"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 4"))
                .expectNextMatches(m -> m.getSceneDescription().equals("Scene 5"))
                .verifyComplete();
    }

}
