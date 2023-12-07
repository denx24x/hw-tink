package com.academy.fintech.origination.core.product_engine.client.rest;

import org.springframework.web.client.RestTemplate;

public class ProductEngineRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final int port;
    private final String host;

    public ProductEngineRestClient(ProductEngineRestClientConfiguration property) {
        this.port = property.port();
        this.host = property.host();
    }

    public void createAgreement(){

    }

}
