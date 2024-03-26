package com.academy.fintech.origination.core.integration.pe.rest;

import com.academy.fintech.origination.configuration.integration.pe.ProductEngineRestClientProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductEngineRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url;

    public ProductEngineRestClient(ProductEngineRestClientProperty productEngineRestClientProperty) {
        this.url = productEngineRestClientProperty.url();
    }
}
