package com.academy.fintech.scoring.public_interface.payment.dto;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

public record PaymentDto (
        int id,
        Date payment_date,
        BigDecimal period_payment,
        BigDecimal interest_payment,
        BigDecimal principal_payment,
        String status,
        int period_number
) {
}
