package com.academy.fintech.pe.balance;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@Builder
@Setter
@Getter
@Table(name = "balance")
public class Balance {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;
    BigDecimal balance;
    @Column(name = "agreement_id")
    Integer agreementId;
}
