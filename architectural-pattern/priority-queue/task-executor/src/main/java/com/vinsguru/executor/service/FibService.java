package com.vinsguru.executor.service;

import org.springframework.stereotype.Service;

@Service
public class FibService {

    // intentional - 2^N
    public long calculate(int n){
        if(n < 2)
            return n;
        return calculate(n - 1) + calculate(n - 2);
    }

}
