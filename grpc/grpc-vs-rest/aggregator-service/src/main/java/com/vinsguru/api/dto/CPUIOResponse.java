package com.vinsguru.api.dto;

import java.util.Map;

public class CPUIOResponse {

    private Map<Object, Object> map;
    private String content;

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
