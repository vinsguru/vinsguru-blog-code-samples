package com.vinsguru.cqrspattern.controller.query;

import com.vinsguru.cqrspattern.dto.PurchaseOrderSummaryDto;
import com.vinsguru.cqrspattern.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("po")
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "false")
public class OrderQueryController {

    @Autowired
    private OrderQueryService orderQueryService;

    @GetMapping("/summary")
    public List<PurchaseOrderSummaryDto> getSummary(){
        return this.orderQueryService.getSaleSummaryGroupByState();
    }

    @GetMapping("/summary/{state}")
    public PurchaseOrderSummaryDto getStateSummary(@PathVariable String state){
        return this.orderQueryService.getSaleSummaryByState(state);
    }

    @GetMapping("/total-sale")
    public Double getTotalSale(){
        return this.orderQueryService.getTotalSale();
    }

}
