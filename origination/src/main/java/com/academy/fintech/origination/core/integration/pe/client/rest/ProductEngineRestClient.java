package com.academy.fintech.origination.core.integration.pe.client.rest;

import com.academy.fintech.origination.configuration.integration.pe.ProductEngineRestClientProperty;
import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.core.integration.pe.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class ProductEngineRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url;

    public ProductEngineRestClient(ProductEngineRestClientProperty productEngineRestClientProperty) {
        this.url = productEngineRestClientProperty.url();
    }

    public void createAgreement(Application application, Product product){
        this.restTemplate.postForLocation(url + "/createAgreement", Map.of(
        "client_id", application.getClientId(),
        "product_code", product.productCode(),
        "product_version", product.productVersion(),
        "loan_term", product.loanTerm(),
        "disbursement_amount", application.getRequestedDisbursementAmount(),
         "interest", product.interest(),
        "origination_amount", product.originationAmount()
        ));
    }
}
