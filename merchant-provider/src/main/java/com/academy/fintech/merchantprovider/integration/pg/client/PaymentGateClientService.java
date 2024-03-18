package com.academy.fintech.merchantprovider.integration.pg.client;

import com.academy.fintech.merchantprovider.db.transfer.Transfer;
import com.academy.fintech.merchantprovider.integration.pg.client.rest.PaymentGateRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentGateClientService {
    @Autowired
    private PaymentGateRestClient paymentGateRestClient;

    public void notifyPayment(Transfer transfer){
        paymentGateRestClient.notifyPayment(transfer.getId(), transfer.getBalanceId(), transfer.getAmount());
    }
}
