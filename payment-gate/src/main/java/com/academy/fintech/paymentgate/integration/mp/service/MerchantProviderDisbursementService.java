package com.academy.fintech.paymentgate.integration.mp.service;

import com.academy.fintech.paymentgate.integration.mp.client.MerchantProviderClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MerchantProviderDisbursementService {
    @Autowired
    private MerchantProviderClientService merchantProviderClientService;

    public void makeDisbursement(String balanceId,  BigDecimal amount){
        merchantProviderClientService.makeDisbursement(balanceId, amount);
    }
}
