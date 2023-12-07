package com.academy.fintech.origination.core.payment_gate.client.rest;

import org.springframework.web.client.RestTemplate;

public class PaymentGateRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final int port;
    private final String host;

    public PaymentGateRestClient(PaymentGateRestClientConfiguration property) {
        this.port = property.port();
        this.host = property.host();
    }

    public void createAgreement(){

    }

}
