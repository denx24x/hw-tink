package com.academy.fintech.paymentgate.integration.mp.client;

import com.academy.fintech.paymentgate.integration.mp.client.rest.MerchantProviderRestClient;
import com.academy.fintech.paymentgate.integration.mp.dto.TransferCheckResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MerchantProviderClientService {
    @Autowired
    private MerchantProviderRestClient merchantProviderRestClient;

    public Integer makeDisbursement(String balanceId, BigDecimal amount) {
        return merchantProviderRestClient.makeDisbursement(balanceId, amount);
    }

    public TransferCheckResponseDto checkTransfer(int id) {
        return merchantProviderRestClient.checkTransfer(id);
    }
}
