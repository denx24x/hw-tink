package com.academy.fintech.paymentgate.service.payment.v1;

import java.math.BigDecimal;

public interface PaymentServiceV1 {
    public void notifyPayment(String balanceId, BigDecimal amount);
}
