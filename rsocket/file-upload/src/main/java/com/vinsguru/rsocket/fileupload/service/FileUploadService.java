package com.vinsguru.rsocket.fileupload.service;

import com.vinsguru.rsocket.fileupload.model.Status;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${output.file.path:src/test/resources/output}")
    private Path outputPath;

    public Mono<Status> getFilePath(Path path, Flux<ByteBuffer> bufferFlux) throws IOException {
        System.out.println(
                outputPath.resolve(path)
        );
        var os = Files.newOutputStream(outputPath.resolve(path), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return bufferFlux
                    .doOnNext(b -> writeFile(os, b.array()))
                    .doOnComplete(() -> this.closeFile(os))
                    .then(Mono.just(Status.COMPLETED));
    }

    public void writeFile(OutputStream writer, byte[] content)  {
        try{
            writer.write(content);
            writer.flush();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void closeFile(OutputStream writer){
        try {
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
