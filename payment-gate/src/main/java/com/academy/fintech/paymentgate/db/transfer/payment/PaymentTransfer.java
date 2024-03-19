package com.academy.fintech.paymentgate.db.transfer.payment;

import com.academy.fintech.paymentgate.db.transfer.Transfer;
import com.academy.fintech.paymentgate.db.transfer.TransferStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_transfer")
public class PaymentTransfer implements Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "balance_id")
    public String balanceId;
    @Column(name = "transfer_id")
    public Integer transferId;

    public BigDecimal amount;
    @Enumerated(EnumType.STRING)
    public TransferStatus status;
}