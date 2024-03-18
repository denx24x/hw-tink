package com.academy.fintech.merchantprovider.service.transfer;

import com.academy.fintech.merchantprovider.db.transfer.TransferType;

import java.math.BigDecimal;

public interface TransferServiceV1 {

    public int register(int clientId, BigDecimal amount, TransferType type);

    public boolean checkTransfer(int id);
}
