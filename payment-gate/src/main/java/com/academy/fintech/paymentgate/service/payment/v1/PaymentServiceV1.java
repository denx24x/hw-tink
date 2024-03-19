package com.academy.fintech.paymentgate.service.payment.v1;

import java.math.BigDecimal;

public interface PaymentServiceV1 {
    public void registerPayment(int id, String balanceId, BigDecimal amount);
}
