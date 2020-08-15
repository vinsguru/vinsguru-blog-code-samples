package com.vinsguru.rsocket.fileupload.controller;

import com.vinsguru.rsocket.fileupload.model.Constants;
import com.vinsguru.rsocket.fileupload.model.Status;
import com.vinsguru.rsocket.fileupload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService service;

    @MessageMapping("file.upload")
    public Flux<Status> upload(@Headers Map<String, Object> metadata, @Payload Flux<DataBuffer> content) throws IOException {
        var fileName = metadata.get(Constants.FILE_NAME);
        var fileExtn = metadata.get(Constants.FILE_EXTN);
        var path = Paths.get(fileName + "." + fileExtn);
        return Flux.concat(service.uploadFile(path, content), Mono.just(Status.COMPLETED))
                    .onErrorReturn(Status.FAILED);

    }

}
