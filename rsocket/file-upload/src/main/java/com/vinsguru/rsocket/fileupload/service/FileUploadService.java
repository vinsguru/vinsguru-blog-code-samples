package com.vinsguru.rsocket.fileupload.service;

import com.vinsguru.rsocket.fileupload.model.Status;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class FileUploadService {

    private static final Path BASE_PATH = Paths.get("src/test/resources/output");

    public Mono<Status> getFilePath(Path path, Flux<ByteBuffer> bufferFlux) throws IOException {
        var os = Files.newOutputStream(BASE_PATH.resolve(path), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return bufferFlux
                    .doOnNext(b -> {
                        try {
                            writeFile(os, b.array());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .doOnComplete(() -> this.closeFile(os))
                    .then(Mono.just(Status.COMPLETED));
    }

    public void writeFile(OutputStream writer, byte[] content) throws IOException {
        writer.write(content);
        writer.flush();
    }

    public void closeFile(OutputStream writer){
        try {
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
