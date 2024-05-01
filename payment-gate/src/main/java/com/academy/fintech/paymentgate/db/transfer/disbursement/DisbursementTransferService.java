package com.academy.fintech.paymentgate.db.transfer.disbursement;

import java.util.Date;
import java.util.List;

public interface DisbursementTransferService {
    public List<DisbursementTransfer> getUnfinishedTransfers();

    public void markTransferFinished(DisbursementTransfer disbursementTransfer, Date finishDate);

    public void startDisbursementTracking(Integer id, int agreementId);
}
