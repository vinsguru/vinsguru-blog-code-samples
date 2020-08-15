package com.tag.dto;

import java.util.function.Consumer;

public class Result {

    private long result;
    private String calculatedInstance;

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }

    public String getCalculatedInstance() {
        return calculatedInstance;
    }

    public void setCalculatedInstance(String calculatedInstance) {
        this.calculatedInstance = calculatedInstance;
    }

    public static Result newInstance(Consumer<Result> consumer){
        Result result = new Result();
        consumer.accept(result);
        return result;
    }

}
