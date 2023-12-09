package com.academy.fintech.pe.controller.calculation;

import com.academy.fintech.pe.agreement.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CalculationController {
    @Autowired
    private AgreementService agreementService;

    @GetMapping("/getPeriodPayment")
    public BigDecimal getPeriodPayment(){
        return BigDecimal.ZERO;
    }

    @GetMapping("/getMaxOverdue")
    public long getMaxOverdue(@RequestParam long clientId){
        return agreementService.findMaxOverdue(clientId);
    }

    @GetMapping("/hasCredit")
    public boolean hasCredit(@RequestParam long clientId){
        return agreementService.hasCredit(clientId);
    }
}
