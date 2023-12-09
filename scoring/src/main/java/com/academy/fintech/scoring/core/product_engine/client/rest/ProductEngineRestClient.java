package com.academy.fintech.scoring.core.product_engine.client.rest;

import org.springframework.web.client.RestTemplate;

public class ProductEngineRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url;

    public ProductEngineRestClient(ProductEngineRestClientProperty property) {
        this.url = property.url();
    }
SG
    public long getMaxOverdue(long clientId){
        restTemplate.getForEntity(url + "/checkOverdue");

    }

    public boolean hasCredit(long clientId){
        restTemplate.getForEntity();
    }

    public void getPeriodPayment(long clientId){

    }

}
