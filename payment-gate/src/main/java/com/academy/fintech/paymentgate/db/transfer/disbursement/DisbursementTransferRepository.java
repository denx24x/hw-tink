package com.academy.fintech.paymentgate.db.transfer.disbursement;

import com.academy.fintech.paymentgate.db.transfer.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisbursementTransferRepository extends JpaRepository<DisbursementTransfer, Integer> {
    public List<DisbursementTransfer> findByStatus(TransferStatus status);

}
