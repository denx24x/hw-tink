package com.academy.fintech.paymentgate.db.transfer.disbursement;

import com.academy.fintech.paymentgate.db.transfer.Transfer;
import com.academy.fintech.paymentgate.db.transfer.TransferStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="disbursement_transfer")
@Builder
@Getter
@Setter
public class DisbursementTransfer implements Transfer {
    @Id
    public Integer id;
    @Column(name="agreement_id")
    public Integer agreementId;
    @Enumerated(EnumType.STRING)
    public TransferStatus status;

    @Override
    public Integer getTransferId() {
        return this.getId();
    }
}
