package com.academy.fintech.merchantprovider.integration.pg.service;

import com.academy.fintech.merchantprovider.db.transfer.Transfer;
import org.springframework.stereotype.Service;

@Service
public class PaymentGateNotificationServiceImpl implements PaymentGateNotificationService {
    @Override
    public void notifyPayment(Transfer transfer) {

    }
}
