package com.vinsguru.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProbeController {

    private static final String MESSAGE = "Slept for %d ms";

    @Value("${request.processing.time}")
    private long requestProcessingTime;

    @GetMapping("/processing-time/{time}")
    public void setRequestProcessingTime(@PathVariable long time){
        this.requestProcessingTime = time;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() throws InterruptedException {
        //sleep to simulate processing time
        Thread.sleep(requestProcessingTime);
        return ResponseEntity.ok(String.format(MESSAGE, requestProcessingTime));
    }

}
