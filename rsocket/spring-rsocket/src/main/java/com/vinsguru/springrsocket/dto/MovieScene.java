package com.vinsguru.springrsocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class MovieScene {

    private int sceneId;
    private String sceneDescription;

}
