package com.academy.fintech.pe.payment_schedule;

import com.academy.fintech.pe.agreement.Agreement;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payment_schedule")
public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agreement_id", referencedColumnName = "id")
    private Agreement agreement;

}
