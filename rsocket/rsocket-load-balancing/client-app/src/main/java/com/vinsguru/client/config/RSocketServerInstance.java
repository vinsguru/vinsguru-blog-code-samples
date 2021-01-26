package com.vinsguru.client.config;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RSocketServerInstance {
    private String host;
    private int port;
}
