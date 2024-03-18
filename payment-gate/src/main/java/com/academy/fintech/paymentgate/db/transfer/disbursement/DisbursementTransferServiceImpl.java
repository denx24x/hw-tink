package com.academy.fintech.paymentgate.db.transfer.disbursement;

import com.academy.fintech.paymentgate.db.transfer.TransferStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DisbursementTransferServiceImpl implements DisbursementTransferService{
    @Autowired
    private DisbursementTransferRepository disbursementTransferRepository;

    public List<DisbursementTransfer> getUnfinishedTransfers(){
        return disbursementTransferRepository.findByStatus(TransferStatus.IN_PROGRESS);
    }

    @Override
    public void markTransferFinished(DisbursementTransfer disbursementTransfer) {
        disbursementTransfer.setStatus(TransferStatus.IN_PROGRESS);
        disbursementTransferRepository.save(disbursementTransfer);
    }
}
