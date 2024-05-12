package com.academy.fintech.origination.exporter.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationTransactionRepository extends JpaRepository<ApplicationTransaction, Integer> {
    @Query(value = "update transactions_application\n" +
            "                   set transaction_status = 'PROCESSING',\n" +
            "                       updated_at = clock_timestamp()\n" +
            "                 where id = any(select et.id\n" +
            "                                  from transactions_application et\n" +
            "                                 where et.status = 'NEW'\n" +
            "                                   for update skip locked)\n" +
            "                 returning *",
            nativeQuery = true)
    public List<ApplicationTransaction> findWithNewStatus();
}
