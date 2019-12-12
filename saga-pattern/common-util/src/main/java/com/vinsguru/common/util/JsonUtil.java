package com.vinsguru.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static <T> String objectToString(T t){
        String value = null;
        try {
            value = new ObjectMapper().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static <T> T stringToObject(String str, Class<? extends T> klass){
        try {
           return new ObjectMapper().readValue(str, klass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
