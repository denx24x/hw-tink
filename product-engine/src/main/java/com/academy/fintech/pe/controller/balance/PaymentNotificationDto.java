package com.academy.fintech.pe.controller.balance;

import java.math.BigDecimal;

public record PaymentNotificationDto (
        String balance_id,
        BigDecimal amount
){

}