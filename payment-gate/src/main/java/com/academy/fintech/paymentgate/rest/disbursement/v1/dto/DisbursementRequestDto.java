package com.academy.fintech.paymentgate.rest.disbursement.v1.dto;

import java.math.BigDecimal;

public record DisbursementRequestDto (
    String client_balance_id,
    BigDecimal amount
){
}
