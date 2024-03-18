package com.academy.fintech.merchantprovider.db.transfer;

import java.math.BigDecimal;
import java.util.Date;

public interface TransferService {
    public Transfer addTransfer(String balanceId, BigDecimal amount, TransferType type, Date finishTime);

    public Date getTransferFinishTime(int id);
}
