package com.academy.fintech.pe.balance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "balance")
public class Balance {
    @Id
    String id;
    BigDecimal balance;
    @Column(name = "agreement_id")
    Integer agreementId;
}
