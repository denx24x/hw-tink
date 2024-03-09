package com.academy.fintech.pe.controller.calculation;

import com.academy.fintech.pe.agreement.AgreementService;
import com.academy.fintech.pe.payment.Payment;
import com.academy.fintech.pe.payment_schedule.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
public class CalculationController {
    @Autowired
    private AgreementService agreementService;

    @Autowired
    private PaymentScheduleService paymentScheduleService;

    @GetMapping("/getMaxOverdue")
    public long getMaxOverdue(@RequestParam(name = "client_id") long clientId) {
        return agreementService.findMaxOverdue(clientId);
    }

    @GetMapping("/hasCredit")
    public boolean hasCredit(@RequestParam(name = "client_id") long clientId) {
        return agreementService.hasCredit(clientId);
    }

    @GetMapping("/calculateSchedule")
    public List<Payment> calculateSchedule(
            @RequestParam int loan_term,
            @RequestParam(name = "principal_amount") BigDecimal principalAmount,
            @RequestParam BigDecimal interest,
            @RequestParam(name = "initial_date") Date initialDate
    ) {
        return paymentScheduleService.createSchedulePayments(
                loan_term,
                principalAmount,
                interest,
                initialDate
        );
    }
}
