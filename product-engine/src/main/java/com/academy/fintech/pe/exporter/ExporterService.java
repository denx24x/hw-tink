package com.academy.fintech.pe.exporter;

import com.academy.fintech.pe.agreement.Agreement;
import com.academy.fintech.pe.exporter.db.AgreementTransaction;
import com.academy.fintech.pe.exporter.db.AgreementTransactionRepository;
import com.academy.fintech.transactional_exporter.db.Transaction;
import com.academy.fintech.transactional_exporter.db.TransactionService;
import com.academy.fintech.transactional_exporter.db.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExporterService implements TransactionService<AgreementTransaction> {
    @Autowired
    private AgreementTransactionRepository exporterRepository;

    public void exportAgreement(Agreement agreement) {
        AgreementTransaction agreementTransaction = AgreementTransaction.builder()
                .agreementId(agreement.getId())
                .status(agreement.getStatus().toString())
                .disbursementDate(agreement.getDisbursementDate())
                .nextPaymentDate(agreement.getNextPaymentDate())
                .build();
        exporterRepository.save(agreementTransaction);
    }

    @Override
    public List<? extends Transaction> findWithNewStatus() {
        return exporterRepository.findWithNewStatus();
    }

    @Override
    public void updateStatusById(TransactionStatus transactionStatus, AgreementTransaction transaction) {
        transaction.setStatus(transactionStatus.toString());
        exporterRepository.save(transaction);
    }
}
