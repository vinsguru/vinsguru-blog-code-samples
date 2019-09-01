package com.vinsguru.dto;

public class EngineDto {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EngineDto{" +
                "type='" + type + '\'' +
                '}';
    }
}
