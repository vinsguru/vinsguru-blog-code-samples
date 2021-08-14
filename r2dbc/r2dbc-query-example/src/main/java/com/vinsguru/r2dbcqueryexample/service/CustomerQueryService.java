package com.vinsguru.r2dbcqueryexample.service;

import com.vinsguru.r2dbcqueryexample.entity.Customer;
import com.vinsguru.r2dbcqueryexample.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class CustomerQueryService {

    @Autowired
    private CustomerRepository repository;

    private final Map<String, String> countryMap = Map.of(
            "BR", "Brazil",
            "CH", "China"
    );

    public Flux<Customer> search(Customer customer){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withTransformer("country", op -> op.map(c -> countryMap.getOrDefault(c, "UNKNOWN")));
        return this.repository.findAll(Example.of(customer, matcher));
    }

}