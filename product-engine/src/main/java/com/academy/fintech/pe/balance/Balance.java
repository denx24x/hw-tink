package com.academy.fintech.pe.balance;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Table(name = "balance")
public class Balance {
    @Id
    String id;
    BigDecimal balance;
    Integer agreement_id;
}
