package com.academy.fintech.paymentgate.integration.mp.service;

import com.academy.fintech.paymentgate.integration.mp.client.MerchantProviderClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MerchantProviderDisbursementService {
    @Autowired
    private MerchantProviderClientService merchantProviderClientService;

    public Integer makeDisbursement(String balanceId, BigDecimal amount) {
        return merchantProviderClientService.makeDisbursement(balanceId, amount);
    }
}
