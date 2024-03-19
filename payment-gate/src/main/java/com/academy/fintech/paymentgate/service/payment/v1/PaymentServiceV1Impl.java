package com.academy.fintech.paymentgate.service.payment.v1;

import com.academy.fintech.paymentgate.db.transfer.payment.PaymentTransferService;
import com.academy.fintech.paymentgate.integration.pe.service.ProductEnginePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceV1Impl implements PaymentServiceV1 {
    @Autowired
    private PaymentTransferService paymentTransferService;
    @Override
    public void registerPayment(int id, String balanceId, BigDecimal amount) {
        paymentTransferService.startPaymentTracking(id, balanceId, amount);
    }
}
