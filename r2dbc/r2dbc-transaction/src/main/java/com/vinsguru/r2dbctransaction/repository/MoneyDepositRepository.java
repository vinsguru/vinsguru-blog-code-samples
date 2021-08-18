package com.vinsguru.r2dbctransaction.repository;

import com.vinsguru.r2dbctransaction.entity.MoneyDepositEvent;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyDepositRepository extends ReactiveCrudRepository<MoneyDepositEvent, Integer> {
}
