package com.academy.fintech.pe.overdue_balance;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Builder
@Setter
@Getter
@Table(name = "overdue_balance")
public class OverdueBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    BigDecimal balance;
    @Column(name = "agreement_id")
    Integer agreementId;
}
