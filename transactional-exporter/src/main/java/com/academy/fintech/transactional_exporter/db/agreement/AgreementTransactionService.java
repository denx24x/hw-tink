package com.academy.fintech.transactional_exporter.db.agreement;

import com.academy.fintech.transactional_exporter.db.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgreementTransactionService {
    @Autowired
    private AgreementTransactionRepository agreementTransactionRepository;

    public List<AgreementTransaction> findWithNewStatus(){
        return agreementTransactionRepository.findWithNewStatus();
    }
}
