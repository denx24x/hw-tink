package com.academy.fintech.paymentgate.db.transfer.payment;

import java.util.List;

public interface PaymentTransferService {
    public List<PaymentTransfer> getUnfinishedTransfers();

    public void markTransferFinished(PaymentTransfer paymentTransfer);
}
