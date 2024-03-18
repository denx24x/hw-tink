package com.academy.fintech.paymentgate.db.transfer.payment;

import com.academy.fintech.paymentgate.db.transfer.TransferStatus;
import com.academy.fintech.paymentgate.db.transfer.disbursement.DisbursementTransfer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaymentTransferServiceImpl implements PaymentTransferService {
    @Autowired
    private PaymentTransferRepository paymentTransferRepository;
    @Override
    public List<PaymentTransfer> getUnfinishedTransfers() {
        return paymentTransferRepository.findByStatus(TransferStatus.IN_PROGRESS);
    }

    @Override
    public void markTransferFinished(PaymentTransfer paymentTransfer) {
        paymentTransfer.setStatus(TransferStatus.IN_PROGRESS);
        paymentTransferRepository.save(paymentTransfer);
    }
}
