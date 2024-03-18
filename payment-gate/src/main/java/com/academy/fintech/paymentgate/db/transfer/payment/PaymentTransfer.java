package com.academy.fintech.paymentgate.db.transfer.payment;

import com.academy.fintech.paymentgate.db.transfer.Transfer;
import com.academy.fintech.paymentgate.db.transfer.TransferStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="payment_transfer")
@Builder
@Getter
@Setter
public class PaymentTransfer implements Transfer {
    @Id
    public Integer id;
    @Column(name = "balance_id")
    public String balanceId;

    public BigDecimal amount;
    @Enumerated(EnumType.STRING)
    public TransferStatus status;

    @Override
    public Integer getTransferId() {
        return this.getId();
    }
}