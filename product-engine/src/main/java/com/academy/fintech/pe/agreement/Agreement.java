package com.academy.fintech.pe.agreement;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "agreement")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "client_id")
    private int clientId;
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "product_version")
    private String productVersion;
    @Column(name = "loan_term")
    private int loanTerm;
    @Column(name = "principal_amount")
    private BigDecimal principalAmount;
    private BigDecimal interest;
    @Column(name = "origination_amount")
    private BigDecimal originationAmount;
    @Enumerated(EnumType.STRING)
    private AgreementStatus status;
    @Column(name = "disbursement_date")
    private Date disbursementDate;
    @Column(name = "next_payment_date")
    private Date nextPaymentDate;
}