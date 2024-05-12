package com.academy.fintech.transactional_exporter.db;

import java.util.List;

public interface TransactionService<T extends Transaction> {
    public List<? extends Transaction> findWithNewStatus();

    public void updateStatusById(TransactionStatus transactionStatus, T transaction);
}
