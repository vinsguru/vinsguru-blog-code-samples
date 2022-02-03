package com.vinsguru.webfluxsse.producer;

import lombok.Data;

import java.io.Serializable;

@Data
public class Joke implements Serializable {

    private static final String JOKE_FORMAT = "Q: %s \nA: %s";

    private String setup;
    private String punchline;

    @Override
    public String toString() {
        return String.format(JOKE_FORMAT, this.setup, this.punchline);
    }

}
