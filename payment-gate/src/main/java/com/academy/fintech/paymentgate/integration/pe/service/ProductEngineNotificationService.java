package com.academy.fintech.paymentgate.integration.pe.service;

import com.academy.fintech.paymentgate.integration.pe.client.ProductEngineClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ProductEngineNotificationService {
    @Autowired
    private ProductEngineClientService productEngineClientService;

    public void notifyPayment(String balanceId, BigDecimal amount, Date finishDate) {
        productEngineClientService.notifyPayment(balanceId, amount, finishDate);
    }

    public void notifyDisbursementFinished(int agreementId, Date finishDate) {
        productEngineClientService.notifyDisbursementFinished(agreementId, finishDate);
    }
}
