package com.vinsguru.r2dbcqueryexample.repository;

import com.vinsguru.r2dbcqueryexample.entity.Customer;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer>, ReactiveQueryByExampleExecutor<Customer> {
}
