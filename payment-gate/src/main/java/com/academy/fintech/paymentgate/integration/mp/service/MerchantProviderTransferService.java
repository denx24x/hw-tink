package com.academy.fintech.paymentgate.integration.mp.service;

import com.academy.fintech.paymentgate.integration.mp.client.MerchantProviderClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantProviderTransferService {
    @Autowired
    private MerchantProviderClientService merchantProviderClientService;

    public Boolean checkTransfer(int id) {
        return merchantProviderClientService.checkTransfer(id);
    }
}
