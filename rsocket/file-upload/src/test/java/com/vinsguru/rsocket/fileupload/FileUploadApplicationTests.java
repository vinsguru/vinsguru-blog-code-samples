package com.vinsguru.rsocket.fileupload;

import com.vinsguru.rsocket.fileupload.model.Constants;
import com.vinsguru.rsocket.fileupload.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class FileUploadApplicationTests {

    @Autowired
    private Mono<RSocketRequester> rSocketRequester;

    @Value("classpath:input/java_tutorial.pdf")
    private Resource resource;

    @Test
    public void uploadFile() {

        // read input file as 4096 chunks
        Flux<DataBuffer> readFlux = DataBufferUtils.read(resource, new DefaultDataBufferFactory(), 4096)
                .doOnNext(s -> System.out.println("Sent"));

        // rsocket request
        this.rSocketRequester
                .map(r -> r.route("file.upload")
                        .metadata(metadataSpec -> {
                            metadataSpec.metadata("pdf", MimeType.valueOf(Constants.MIME_FILE_EXTENSION));
                            metadataSpec.metadata("output", MimeType.valueOf(Constants.MIME_FILE_NAME));
                        })
                        .data(readFlux)
                )
                .flatMapMany(r -> r.retrieveFlux(Status.class))
                .doOnNext(s -> System.out.println("Upload Status : " + s))
                .subscribe();
    }

}
