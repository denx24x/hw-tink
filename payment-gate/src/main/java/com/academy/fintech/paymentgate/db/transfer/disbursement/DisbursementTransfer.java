package com.academy.fintech.paymentgate.db.transfer.disbursement;

import com.academy.fintech.paymentgate.db.transfer.Transfer;
import com.academy.fintech.paymentgate.db.transfer.TransferStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "disbursement_transfer")
public class DisbursementTransfer implements Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "transfer_id")
    public Integer transferId;
    @Column(name = "agreement_id")
    public Integer agreementId;
    @Enumerated(EnumType.STRING)
    public TransferStatus status;
    @Column(name = "finish_date")
    public Date finishDate;
}
