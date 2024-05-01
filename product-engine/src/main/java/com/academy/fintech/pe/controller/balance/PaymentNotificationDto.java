package com.academy.fintech.pe.controller.balance;

import java.math.BigDecimal;
import java.util.Date;

public record PaymentNotificationDto(
        String balance_id,
        BigDecimal amount,
        Date finishDate
) {

}