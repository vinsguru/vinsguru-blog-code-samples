package com.vinsguru.rsocket.fileupload;

import com.vinsguru.rsocket.fileupload.model.Constants;
import com.vinsguru.rsocket.fileupload.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootTest
class FileUploadApplicationTests {

    @Autowired
    private Mono<RSocketRequester> rSocketRequester;

    @Test
    public void uploadFile() throws IOException, InterruptedException {
        Path path = Paths.get("src/test/resources/input/ip.mp4");
        Flux<ByteBuffer> flux = Flux.using(this.ipStreamSupplier(path), streamProcessor(), closeStream());
        this.rSocketRequester
                                        .map(r -> r.route("file.upload")
                                                .metadata(metadataSpec -> {
                                                    metadataSpec.metadata("mp4", MimeType.valueOf(Constants.MIME_FILE_EXTENSION));
                                                    metadataSpec.metadata("output", MimeType.valueOf(Constants.MIME_FILE_NAME));
                                                })
                                                .data(flux)
                                        )
                                        .flatMapMany(r -> r.retrieveFlux(Status.class))
                                        .doOnNext(s -> System.out.println("Upload Status : " + s))
                                        .subscribe();
        Thread.sleep(5000);

    }

    private Callable<InputStream> ipStreamSupplier(Path path){
        return () -> Files.newInputStream(path);
    }

    private Function<InputStream, Flux<ByteBuffer>> streamProcessor(){
        final byte[] bytes = new byte[4096];
        BiFunction<InputStream, SynchronousSink<ByteBuffer>, InputStream> generator = (stream, sink) -> {
            try {
                int size = stream.read(bytes);
                if(size > 0)
                    sink.next(ByteBuffer.wrap(Arrays.copyOfRange(bytes, 0, size)));
                else
                    sink.complete();
            } catch (IOException e) {
                sink.error(e);
            }
            return stream;
        };
        return is -> Flux.generate(() -> is, generator);
    }

    private Consumer<InputStream> closeStream(){
        return is -> {
            try {
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        };
    }

}
