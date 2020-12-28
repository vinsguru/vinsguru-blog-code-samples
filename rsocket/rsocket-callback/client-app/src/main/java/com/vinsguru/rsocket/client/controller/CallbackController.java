package com.vinsguru.rsocket.client.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class CallbackController {

    // server will callback for any update
    @MessageMapping("request-status-callback")
    public void message(String message){
        System.out.println(LocalDateTime.now() + " :: " + message);
    }

}
