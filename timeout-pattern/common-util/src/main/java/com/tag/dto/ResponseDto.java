package com.tag.dto;

import java.util.function.Consumer;

public class ResponseDto {

    private String type;
    private int input;
    private Result response;

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

    public Result getResponse() {
        return response;
    }

    public void setResponse(Result response) {
        this.response = response;
    }

    public static ResponseDto newInstance(Consumer<ResponseDto> consumer){
        ResponseDto result = new ResponseDto();
        consumer.accept(result);
        return result;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "type='" + type + '\'' +
                ", input=" + input +
                ", response=" + response +
                '}';
    }
}
