package com.academy.fintech.pe.agreement;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="agreement")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int client_id;
    private String product_code;
    private String product_version;
    private int loan_term;
    private BigDecimal principal_amount;
    private BigDecimal interest;
    private BigDecimal origination_amount;
    @Enumerated(EnumType.STRING)
    private AgreementStatus status;
    private Date disbursement_date;
    private Date next_payment_date;
}