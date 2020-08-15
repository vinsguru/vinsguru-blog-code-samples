package com.vinsguru.kafkasagapattern.orderservice.eventhandlers;

import com.vinsguru.kafkasagapattern.model.enums.OrderStatus;
import com.vinsguru.kafkasagapattern.model.evt.PaymentEvent;
import com.vinsguru.kafkasagapattern.model.enums.PaymentStatus;
import com.vinsguru.kafkasagapattern.orderservice.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentEventConsumerService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    public void consumePaymentEvent(PaymentEvent paymentEvent){
        this.purchaseOrderRepository.findById(paymentEvent.getOrderId())
                    .ifPresent(purchaseOrder -> {
                        purchaseOrder.setStatus(paymentEvent.getStatus().equals(PaymentStatus.APPROVED) ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED);
                        this.purchaseOrderRepository.save(purchaseOrder);
                    });
    }

}
