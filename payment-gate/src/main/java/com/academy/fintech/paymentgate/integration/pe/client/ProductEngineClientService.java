package com.academy.fintech.paymentgate.integration.pe.client;

import com.academy.fintech.paymentgate.integration.pe.client.rest.ProductEngineRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ProductEngineClientService {
    @Autowired
    private ProductEngineRestClient productEngineRestClient;

    public void notifyPayment(String balanceId, BigDecimal amount, Date finishDate) {
        productEngineRestClient.notifyPayment(balanceId, amount, finishDate);
    }

    public void notifyDisbursementFinished(int agreementId, Date finishDate) {
        productEngineRestClient.notifyDisbursementFinished(agreementId, finishDate);
    }
}
