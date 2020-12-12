package com.vinsguru.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class ObjectUtil {

    public static <T> Optional<T> toObject(byte[] src, Class<T> type){
        try{
            ObjectMapper mapper = new ObjectMapper();
            return Optional.of(mapper.readValue(src, type));
        }catch (Exception e){
            return  Optional.empty();
        }
    }

    public static byte[] toBytes(Object o){
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsBytes(o);
        }catch (Exception e){
            return new byte[0];
        }
    }

}
