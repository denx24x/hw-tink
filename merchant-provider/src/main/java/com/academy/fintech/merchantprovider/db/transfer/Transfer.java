package com.academy.fintech.merchantprovider.db.transfer;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;
        @Column(name="balance_id")
        String balanceId;
        @Enumerated(EnumType.STRING)
        TransferType type;
        BigDecimal amount;
        @Column(name = "finish_time")
        Date finishTime;
}
