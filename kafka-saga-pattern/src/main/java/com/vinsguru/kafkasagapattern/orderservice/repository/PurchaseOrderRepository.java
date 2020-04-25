package com.vinsguru.kafkasagapattern.orderservice.repository;

import com.vinsguru.kafkasagapattern.orderservice.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}
