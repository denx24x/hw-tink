package com.academy.fintech.paymentgate.service.disbursement.v1;

import com.academy.fintech.paymentgate.integration.mp.service.MerchantProviderDisbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DisbursementServiceV1Impl implements DisbursementServiceV1 {
    @Autowired
    private MerchantProviderDisbursementService disbursementService;
    @Override
    public void makeDisbursement(String balanceId, BigDecimal amount) {
        disbursementService.makeDisbursement(balanceId, amount);
    }
}
