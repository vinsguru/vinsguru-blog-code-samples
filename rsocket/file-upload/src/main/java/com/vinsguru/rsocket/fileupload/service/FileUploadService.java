package com.vinsguru.rsocket.fileupload.service;

import com.vinsguru.rsocket.fileupload.model.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class FileUploadService {

    @Value("${output.file.path:src/test/resources/output}")
    private Path outputPath;

    public Flux<Status> uploadFile(Path path, Flux<DataBuffer> bufferFlux) throws IOException {
        Path opPath = outputPath.resolve(path);
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(opPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        return DataBufferUtils.write(bufferFlux, channel)
                .map(b -> Status.CHUNK_COMPLETED);
    }

}
