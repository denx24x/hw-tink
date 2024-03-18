package com.academy.fintech.paymentgate.rest.payment.v1.dto;

import java.math.BigDecimal;

public record PaymentRequestDto(
        int id,
        String balance_id,
        BigDecimal amount
) {
}
