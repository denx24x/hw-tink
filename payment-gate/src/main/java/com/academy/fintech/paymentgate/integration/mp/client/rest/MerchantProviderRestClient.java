package com.academy.fintech.paymentgate.integration.mp.client.rest;

import com.academy.fintech.paymentgate.config.integration.mp.MerchantProviderRestClientProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class MerchantProviderRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url;

    public MerchantProviderRestClient(MerchantProviderRestClientProperty paymentGateRestClientProperty) {
        this.url = paymentGateRestClientProperty.url();
    }

    public Integer makeDisbursement(String balanceId, BigDecimal amount) {
        return restTemplate.postForObject(url + "/disbursement", Map.of(
                "balance_id", balanceId,
                "amount", amount
        ), Integer.class);
    }

    public Boolean checkTransfer(int id) {
        return restTemplate.getForObject(url + "/check_transfer?id={id}", Boolean.class, Map.of("id", id));
    }
}
