package com.academy.fintech.scoring.core.product_engine.client.rest;

import com.academy.fintech.scoring.public_interface.payment.dto.PaymentDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class ProductEngineRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url;

    public ProductEngineRestClient(com.academy.fintech.scoring.core.product_engine.client.rest.ProductEngineRestClientProperty productEngineRestClientProperty) {
        this.url = productEngineRestClientProperty.url();
    }

    public long getMaxOverdue(long clientId) {
        return restTemplate.getForObject(url + "/getMaxOverdue?client_id={clientId}", Integer.class, Map.of("clientId", clientId));
    }

    public boolean hasCredit(long clientId) {
        return restTemplate.getForObject(url + "/hasCredit?client_id={clientId}", Boolean.class, Map.of("clientId", clientId));
    }

    public List<PaymentDto> calcPaymentSchedule(
            int loanTerm,
            BigDecimal principalAmount,
            BigDecimal interest
    ) {


        PaymentDto[] result = restTemplate.getForObject(url + "/calculateSchedule?loan_term={loan_term}&principal_amount={principal_amount}&interest={interest}&initial_date={initialDate}", PaymentDto[].class, Map.of(
                "loan_term", loanTerm,
                "principal_amount", principalAmount,
                "interest", interest,
                "initialDate", new Date()
        ));
        return Arrays.asList(result);
    }

}
