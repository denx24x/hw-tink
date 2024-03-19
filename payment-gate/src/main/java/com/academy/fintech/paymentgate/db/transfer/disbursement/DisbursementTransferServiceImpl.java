package com.academy.fintech.paymentgate.db.transfer.disbursement;

import com.academy.fintech.paymentgate.db.transfer.TransferStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DisbursementTransferServiceImpl implements DisbursementTransferService {
    @Autowired
    private DisbursementTransferRepository disbursementTransferRepository;

    public List<DisbursementTransfer> getUnfinishedTransfers() {
        return disbursementTransferRepository.findByStatus(TransferStatus.IN_PROGRESS);
    }

    @Override
    public void markTransferFinished(DisbursementTransfer disbursementTransfer) {
        disbursementTransfer.setStatus(TransferStatus.FINISHED);
        disbursementTransferRepository.save(disbursementTransfer);
    }

    @Override
    public void startDisbursementTracking(Integer id, int agreementId) {
        DisbursementTransfer transfer = DisbursementTransfer.builder()
                .transferId(id)
                .agreementId(agreementId)
                .status(TransferStatus.IN_PROGRESS)
                .build();
        disbursementTransferRepository.save(transfer);
    }
}
