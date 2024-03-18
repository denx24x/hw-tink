package com.academy.fintech.paymentgate.db.transfer.payment;

import com.academy.fintech.paymentgate.db.transfer.disbursement.DisbursementTransfer;

import java.util.List;

public interface PaymentTransferService {
    public List<PaymentTransfer> getUnfinishedTransfers();

    public void markTransferFinished(PaymentTransfer paymentTransfer);
}
