package com.academy.fintech.paymentgate.db.transfer.payment;

import com.academy.fintech.paymentgate.db.transfer.TransferStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentTransferServiceImpl implements PaymentTransferService {
    @Autowired
    private PaymentTransferRepository paymentTransferRepository;

    @Override
    public List<PaymentTransfer> getUnfinishedTransfers() {
        return paymentTransferRepository.findByStatus(TransferStatus.IN_PROGRESS);
    }

    @Override
    public void markTransferFinished(PaymentTransfer paymentTransfer) {
        paymentTransfer.setStatus(TransferStatus.FINISHED);
        paymentTransferRepository.save(paymentTransfer);
    }

    @Override
    public void startPaymentTracking(Integer id, String balanceId, BigDecimal amount) {
        PaymentTransfer paymentTransfer = PaymentTransfer.builder()
                .transferId(id)
                .balanceId(balanceId)
                .amount(amount)
                .status(TransferStatus.IN_PROGRESS).build();
        paymentTransferRepository.save(paymentTransfer);
    }
}
