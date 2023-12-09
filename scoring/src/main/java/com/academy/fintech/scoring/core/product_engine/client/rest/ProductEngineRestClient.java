package com.academy.fintech.scoring.core.product_engine.client.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

public class ProductEngineRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url;

    public ProductEngineRestClient(ProductEngineRestClientProperty property) {
        this.url = property.url();
    }
SG
    public long getMaxOverdue(long clientId){
        return restTemplate.getForObject(url + "/getMaxOverdue" + clientId, Integer.class, Map.of("clientId", clientId));
    }

    public boolean hasCredit(long clientId){
        return restTemplate.getForObject(url + "/hasCredit" + clientId, Boolean.class, Map.of("clientId", clientId));
    }

    public void getPeriodPayment(long clientId){

    }

}
