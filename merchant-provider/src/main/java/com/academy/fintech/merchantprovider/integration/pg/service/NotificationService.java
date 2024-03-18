package com.academy.fintech.merchantprovider.integration.pg.service;

import com.academy.fintech.merchantprovider.db.transfer.Transfer;

public interface NotificationService {
    public void notifyPayment(Transfer transfer);
}
