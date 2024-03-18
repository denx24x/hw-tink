package com.academy.fintech.paymentgate.db.transfer.payment;

import com.academy.fintech.paymentgate.db.transfer.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentTransferRepository extends JpaRepository<PaymentTransfer, Integer> {
    public List<PaymentTransfer> findByStatus(TransferStatus status);
}
