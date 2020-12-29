package com.vinsguru.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestService {

    @Value("${rest.square.service.endpoint}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Object getUnaryResponse(int number){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= number ; i++) {
            ResponseEntity<Integer> responseEntity = this.restTemplate.getForEntity(this.baseUrl + String.format("/rest/square/unary/%d", i), Integer.class);
            map.put(i, responseEntity.getBody());
        }
        return map;
    }

}
