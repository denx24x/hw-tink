package com.academy.fintech.origination.exporter;

import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.exporter.db.ApplicationTransaction;
import com.academy.fintech.origination.exporter.db.ApplicationTransactionRepository;
import com.academy.fintech.transactional_exporter.db.TransactionService;
import com.academy.fintech.transactional_exporter.db.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExporterService implements TransactionService<ApplicationTransaction> {
    @Autowired
    private ApplicationTransactionRepository exporterRepository;

    public void exportApplication(Application application) {
        ApplicationTransaction applicationTransaction = ApplicationTransaction.builder()
                .applicationId(application.getId())
                .status(application.getStatus().toString())
                .transactionStatus(TransactionStatus.NEW)
                .build();
        exporterRepository.save(applicationTransaction);
    }

    @Override
    public List<ApplicationTransaction> findWithStatus(TransactionStatus status) {
        return exporterRepository.findWithStatus(status);
    }

    @Override
    public void updateStatusById(TransactionStatus transactionStatus, ApplicationTransaction applicationTransaction) {
        applicationTransaction.setStatus(transactionStatus.toString());
        exporterRepository.save(applicationTransaction);
    }
}
