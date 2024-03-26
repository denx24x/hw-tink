package com.academy.fintech.merchantprovider.service.transfer.v1;

import com.academy.fintech.merchantprovider.db.transfer.TransferType;

import java.math.BigDecimal;
import java.util.Date;

public interface TransferServiceV1 {

    public int register(String balanceId, BigDecimal amount, TransferType type);

    public boolean checkTransfer(int id);

    public Date getTransferFinishTime(int id);
}
