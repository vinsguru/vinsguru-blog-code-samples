package fib.service;


import org.springframework.stereotype.Service;

@Service
public class FibService {

    public long calculateFib(final int number){
        return getFib(number);
    }

    // 2^N logic is intentional to simulate CPU intensive tasks
    private long getFib(int number){
        if(number <= 0)
            return 0;
        else if(number <= 2 )
            return 1;
        return getFib(number-1) + getFib(number-2);
    }
}
