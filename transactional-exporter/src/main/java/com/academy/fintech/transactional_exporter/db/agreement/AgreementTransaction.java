package com.academy.fintech.transactional_exporter.db.agreement;

import com.academy.fintech.transactional_exporter.db.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="transactions_agreement")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgreementTransaction{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name="agreement_id")
    Integer agreementId;
    String status;
    @Column(name="disbursement_date")
    Date disbursementDate;
    @Column(name="next_payment_date")
    Date next_payment_date;
    @CreationTimestamp
    Timestamp createdAt;
    @UpdateTimestamp
    Timestamp updatedAt;
    @Column(name = "transaction_status")
    TransactionStatus transactionStatus;
}