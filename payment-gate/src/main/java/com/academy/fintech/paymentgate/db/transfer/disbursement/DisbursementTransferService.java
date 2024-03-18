package com.academy.fintech.paymentgate.db.transfer.disbursement;

import com.academy.fintech.paymentgate.db.transfer.payment.PaymentTransfer;

import java.util.List;

public interface DisbursementTransferService {
    public List<DisbursementTransfer> getUnfinishedTransfers();

    public void markTransferFinished(DisbursementTransfer disbursementTransfer);
}
