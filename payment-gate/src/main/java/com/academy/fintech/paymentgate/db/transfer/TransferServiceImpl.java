package com.academy.fintech.paymentgate.db.transfer;

import com.academy.fintech.paymentgate.db.transfer.disbursement.DisbursementTransfer;
import com.academy.fintech.paymentgate.db.transfer.disbursement.DisbursementTransferService;
import com.academy.fintech.paymentgate.db.transfer.payment.PaymentTransfer;
import com.academy.fintech.paymentgate.db.transfer.payment.PaymentTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private PaymentTransferService paymentTransferService;
    @Autowired
    private DisbursementTransferService disbursementTransferService;

    @Override
    public List<Transfer> getUnfinishedTransfers() {
        return Stream.concat(paymentTransferService.getUnfinishedTransfers().stream(), disbursementTransferService.getUnfinishedTransfers().stream()).toList();
    }

    @Override
    public void markTransferFinished(Transfer transfer, Date finishDate) {
        if (transfer instanceof PaymentTransfer) {
            paymentTransferService.markTransferFinished((PaymentTransfer) transfer, finishDate);
        } else if (transfer instanceof DisbursementTransfer) {
            disbursementTransferService.markTransferFinished((DisbursementTransfer) transfer, finishDate);
        } else {
            throw new RuntimeException("Unknown transfer type");
        }
    }
}
