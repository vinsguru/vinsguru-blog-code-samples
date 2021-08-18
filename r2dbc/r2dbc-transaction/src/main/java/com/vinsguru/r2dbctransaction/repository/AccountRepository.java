package com.vinsguru.r2dbctransaction.repository;

import com.vinsguru.r2dbctransaction.entity.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<Account, Integer> {
}
