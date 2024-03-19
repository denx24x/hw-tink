package com.academy.fintech.paymentgate.db.transfer.payment;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentTransferService {
    public List<PaymentTransfer> getUnfinishedTransfers();

    public void markTransferFinished(PaymentTransfer paymentTransfer);

    public void startPaymentTracking(Integer id, String balanceId, BigDecimal amount);
}
