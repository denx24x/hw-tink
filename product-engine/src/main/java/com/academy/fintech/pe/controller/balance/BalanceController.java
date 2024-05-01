package com.academy.fintech.pe.controller.balance;

import com.academy.fintech.pe.balance.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @PostMapping("/notifyPayment")
    public void notifyPayment(PaymentNotificationDto request) {
        balanceService.applyPayment(request.balance_id(), request.amount());
    }
}
