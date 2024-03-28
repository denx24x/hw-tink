package com.academy.fintech.pe.scheduler.payment;

import com.academy.fintech.pe.agreement.Agreement;
import com.academy.fintech.pe.agreement.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PaymentScheduler {
    @Autowired
    private AgreementService agreementService;
    @Scheduled(cron = "0 0 0 * * *")
    public void handlePayment(){
        for(Agreement agreement : agreementService.getActiveAgreements()){

        }
    }
}
