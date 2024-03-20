package com.academy.fintech.merchantprovider.integration.pg.service;

import com.academy.fintech.merchantprovider.db.transfer.Transfer;
import com.academy.fintech.merchantprovider.integration.pg.client.PaymentGateClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentGateNotificationServiceImpl implements PaymentGateNotificationService {
    @Autowired
    private PaymentGateClientService paymentGateClientService;

    @Override
    public void notifyPayment(Transfer transfer) {
        paymentGateClientService.notifyPayment(transfer);
    }
}
