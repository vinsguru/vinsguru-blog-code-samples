package com.tag.math.compute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.IntStream;

@Service
public class ComputeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${factservice.host}")
    private String factServiceHost;

    @Value("${fibservice.host}")
    private String fibServiceHost;

    public Long findFib(int number){
        String url = "http://" + fibServiceHost + "/api/fib/" + number;
        return getResponse(url);
    }

    public Long findFact(int number){
        String url =  "http://" + factServiceHost + "/api/fact/" + number;;
        return getResponse(url);
    }

    public Long sumFact(int number){
        return IntStream.rangeClosed(1, number)
                .parallel()
                    .mapToObj(i ->  "http://" + factServiceHost + "/api/fact/" + i)
                    .mapToLong(this::getResponse)
                    .sum();
    }

    private Long getResponse(String url){
        return this.restTemplate.getForObject(url, Long.class);
    }

}
