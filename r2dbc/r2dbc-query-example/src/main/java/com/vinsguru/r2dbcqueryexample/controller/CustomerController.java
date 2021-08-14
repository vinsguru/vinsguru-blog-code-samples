package com.vinsguru.r2dbcqueryexample.controller;

import com.vinsguru.r2dbcqueryexample.entity.Customer;
import com.vinsguru.r2dbcqueryexample.service.CustomerQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerQueryService queryService;

    @GetMapping("search")
    public Flux<Customer> search(Customer customerSearchCriteria){
        return this.queryService.search(customerSearchCriteria);
    }

}
