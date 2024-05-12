package com.academy.fintech.transactional_exporter.db.agreement;

import com.academy.fintech.transactional_exporter.db.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementTransactionRepository extends JpaRepository<AgreementTransaction, Integer> {
    @Query(value = "update transactions_agreement\n" +
            "                   set transaction_status = 'PROCESSING',\n" +
            "                       updated_at = clock_timestamp()\n" +
            "                 where id = any(select et.id\n" +
            "                                  from transactions_agreement et\n" +
            "                                 where et.status = 'NEW'\n" +
            "                                   for update skip locked)\n" +
            "                 returning *",
    nativeQuery = true)
    public List<AgreementTransaction> findWithNewStatus();
}
