package com.academy.fintech.paymentgate.integration.pe.client;

import com.academy.fintech.paymentgate.integration.pe.client.rest.ProductEngineRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductEngineClientService {
    @Autowired
    private ProductEngineRestClient productEngineRestClient;

    public void notifyPayment(String balanceId, BigDecimal amount) {
        productEngineRestClient.notifyPayment(balanceId, amount);
    }

    public void notifyDisbursementFinished(int agreementId){
        productEngineRestClient.notifyDisbursementFinished(agreementId);
    }
}
