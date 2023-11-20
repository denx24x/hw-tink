package com.academy.fintech.pe.payment;

import com.academy.fintech.pe.payment_schedule.PaymentSchedule;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payment_schedule_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="schedule_id", referencedColumnName = "id")
    private PaymentSchedule schedule;
    private Date payment_date;
    private BigDecimal period_payment;
    private BigDecimal interest_payment;
    private BigDecimal principal_payment;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private int period_number;
}
