package com.vinsguru.webclient.reactivewebclient.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Comment {

    private int id;
    private String body;
    private int postId;

}
