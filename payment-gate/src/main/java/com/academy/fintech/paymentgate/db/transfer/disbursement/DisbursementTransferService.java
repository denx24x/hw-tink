package com.academy.fintech.paymentgate.db.transfer.disbursement;

import java.util.List;

public interface DisbursementTransferService {
    public List<DisbursementTransfer> getUnfinishedTransfers();

    public void markTransferFinished(DisbursementTransfer disbursementTransfer);

    public void startDisbursementTracking(Integer id, int agreementId);
}
