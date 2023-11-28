package com.vinsguru.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class ObjectUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> Optional<T> toObject(byte[] src, Class<T> type){
        try{
            return Optional.of(mapper.readValue(src, type));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public static byte[] toBytes(Object o){
        try{
            return mapper.writeValueAsBytes(o);
        }catch (Exception e){
            return new byte[0];
        }
    }

}
