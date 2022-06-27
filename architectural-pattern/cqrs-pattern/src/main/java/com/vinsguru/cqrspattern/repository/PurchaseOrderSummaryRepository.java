package com.vinsguru.cqrspattern.repository;

import com.vinsguru.cqrspattern.entity.PurchaseOrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseOrderSummaryRepository extends JpaRepository<PurchaseOrderSummary, String> {
    Optional<PurchaseOrderSummary> findByState(String state);
}
