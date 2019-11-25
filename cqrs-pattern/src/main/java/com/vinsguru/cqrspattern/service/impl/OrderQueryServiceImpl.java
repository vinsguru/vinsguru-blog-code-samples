package com.vinsguru.cqrspattern.service.impl;

import com.vinsguru.cqrspattern.dto.PurchaseOrderSummaryDto;
import com.vinsguru.cqrspattern.repository.PurchaseOrderSummaryRepository;
import com.vinsguru.cqrspattern.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    @Autowired
    private PurchaseOrderSummaryRepository purchaseOrderSummaryRepository;

    @Override
    public List<PurchaseOrderSummaryDto> getSaleSummary() {
        return this.purchaseOrderSummaryRepository.findAll()
                .stream()
                .map(pos -> {
                    PurchaseOrderSummaryDto dto = new PurchaseOrderSummaryDto();
                    dto.setState(pos.getState());
                    dto.setTotalSale(pos.getTotalSale());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
