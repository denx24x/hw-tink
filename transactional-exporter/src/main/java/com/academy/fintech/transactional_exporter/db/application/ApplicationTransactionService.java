package com.academy.fintech.transactional_exporter.db.application;

import com.academy.fintech.transactional_exporter.db.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationTransactionService {
    @Autowired
    private ApplicationTransactionRepository applicationTransactionRepository;

    public List<ApplicationTransaction> findWithNewStatus(){
        return applicationTransactionRepository.findWithNewStatus();
    }
}
