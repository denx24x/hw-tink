package com.academy.fintech.paymentgate.service.disbursement.v1;

import java.math.BigDecimal;

public interface DisbursementServiceV1 {
    public void makeDisbursement(String balanceId, int agreementId, BigDecimal amount);
}
