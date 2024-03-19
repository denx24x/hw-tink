package com.academy.fintech.paymentgate.integration.pe.service;

import com.academy.fintech.paymentgate.integration.pe.client.ProductEngineClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductEngineNotificationService {
    @Autowired
    private ProductEngineClientService productEngineClientService;

    public void notifyPayment(String balanceId, BigDecimal amount) {
        productEngineClientService.notifyPayment(balanceId, amount);
    }

    public void notifyDisbursementFinished(int agreementId) {
        productEngineClientService.notifyDisbursementFinished(agreementId);
    }
}
