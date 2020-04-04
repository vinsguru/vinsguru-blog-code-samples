package com.vinsguru.webclient.reactivewebclient.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Post {

    private int id;
    private String title;
    private String author;

}
