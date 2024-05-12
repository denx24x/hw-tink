package com.academy.fintech.pe.exporter.db;

import com.academy.fintech.transactional_exporter.db.Transaction;
import com.academy.fintech.transactional_exporter.db.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "transactions_agreement")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgreementTransaction implements Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "agreement_id")
    Integer agreementId;
    String status;
    @Column(name = "disbursement_date")
    Date disbursementDate;
    @Column(name = "next_payment_date")
    Date nextPaymentDate;
    @CreationTimestamp
    Timestamp createdAt;
    @UpdateTimestamp
    Timestamp updatedAt;
    @Column(name = "transaction_status")
    TransactionStatus transactionStatus;

    @Override
    public Integer getKey() {
        return agreementId;
    }
}