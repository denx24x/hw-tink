package com.academy.fintech.paymentgate.integration.pe.client.rest;

import com.academy.fintech.paymentgate.config.integration.pe.ProductEngineRestClientProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Component
public class ProductEngineRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url;

    public ProductEngineRestClient(ProductEngineRestClientProperty paymentGateRestClientProperty) {
        this.url = paymentGateRestClientProperty.url();
    }

    public void notifyPayment(String balanceId, BigDecimal amount, Date finishDate) {
        restTemplate.postForLocation(url + "/notifyPayment", Map.of(
                "client_balance_id", balanceId,
                "amount", amount,
                "finishDate", finishDate
        ));
    }

    public void notifyDisbursementFinished(int agreementId, Date finishDate) {
        restTemplate.postForLocation(url + "/disbursement", Map.of(
                "agreement_id", agreementId,
                "disbursement_date", finishDate
        ));
    }
}
