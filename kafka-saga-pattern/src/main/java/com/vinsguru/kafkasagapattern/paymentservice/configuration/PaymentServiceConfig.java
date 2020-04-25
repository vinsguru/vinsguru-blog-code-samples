package com.vinsguru.kafkasagapattern.paymentservice.configuration;

import com.vinsguru.kafkasagapattern.model.evt.OrderEvent;
import com.vinsguru.kafkasagapattern.model.evt.PaymentEvent;
import com.vinsguru.kafkasagapattern.paymentservice.eventhandlers.OrderEventProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class PaymentServiceConfig {

    @Autowired
    private OrderEventProcessorService orderEventProcessorService;

    @Bean
    public Function<OrderEvent, PaymentEvent> orderEventProcessor(){
        return orderEventProcessorService::processOrderEvent;
    }

}
