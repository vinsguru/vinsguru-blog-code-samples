package com.vinsguru.cqrspattern.service;

import com.vinsguru.cqrspattern.dto.PurchaseOrderSummaryDto;

import java.util.List;

public interface OrderQueryService {
    List<PurchaseOrderSummaryDto> getSaleSummary();
}
