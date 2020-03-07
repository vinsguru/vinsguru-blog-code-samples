package com.tag.math.compute.dto;

public class RequestDto {
    private String type;
    private int input;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "type='" + type + '\'' +
                ", input=" + input +
                '}';
    }
}
