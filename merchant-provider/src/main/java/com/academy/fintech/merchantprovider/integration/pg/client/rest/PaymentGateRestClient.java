package com.academy.fintech.merchantprovider.integration.pg.client.rest;

import com.academy.fintech.merchantprovider.config.integration.pg.PaymentGateRestClientProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class PaymentGateRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url;

    public PaymentGateRestClient(PaymentGateRestClientProperty paymentGateRestClientProperty) {
        this.url = paymentGateRestClientProperty.url();
    }

    public void notifyPayment(int id, int clientId,  BigDecimal amount){
        restTemplate.postForLocation(url + "/payment", Map.of(
                "id", id,
                "client_id", clientId,
                "amount", amount
        ));
    }
}
