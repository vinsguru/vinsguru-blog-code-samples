package com.vinsguru.rsocket.fileupload;

import com.vinsguru.rsocket.fileupload.model.Constants;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeType;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class RSocketConfig {

    @Bean
    public RSocketStrategies rSocketStrategies() {
        return RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2CborEncoder()))
                .decoders(decoders -> decoders.add(new Jackson2CborDecoder()))
                .metadataExtractorRegistry(metadataExtractorRegistry -> {
                    metadataExtractorRegistry.metadataToExtract(MimeType.valueOf(Constants.MIME_FILE_EXTENSION), String.class, Constants.FILE_EXTN);
                    metadataExtractorRegistry.metadataToExtract(MimeType.valueOf(Constants.MIME_FILE_NAME), String.class, Constants.FILE_NAME);
                })
                .build();
    }

    @Bean
    public Mono<RSocketRequester> getRSocketRequester(RSocketRequester.Builder builder){
        return builder
                .rsocketConnector(rSocketConnector -> rSocketConnector.reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2))))
                .connect(TcpClientTransport.create(6565));
    }

}
