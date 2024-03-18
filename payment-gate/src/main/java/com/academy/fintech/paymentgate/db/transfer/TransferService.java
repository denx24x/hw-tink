package com.academy.fintech.paymentgate.db.transfer;

import java.util.List;

public interface TransferService {
    public List<Transfer> getUnfinishedTransfers();

    public void markTransferFinished(Transfer transfer);
}
