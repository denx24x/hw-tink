package com.academy.fintech.paymentgate.service.payment.v1;
import com.academy.fintech.paymentgate.integration.pe.service.ProductEnginePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceV1Impl implements PaymentServiceV1 {
    @Autowired
    private ProductEnginePaymentService paymentService;
    @Override
    public void notifyPayment(String balanceId, BigDecimal amount) {
        paymentService.notifyPayment(balanceId, amount);
    }
}
