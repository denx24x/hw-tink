package com.academy.fintech.pe.scheduler.payment;

import com.academy.fintech.pe.agreement.Agreement;
import com.academy.fintech.pe.agreement.AgreementService;
import com.academy.fintech.pe.balance.Balance;
import com.academy.fintech.pe.balance.BalanceService;
import com.academy.fintech.pe.payment.Payment;
import com.academy.fintech.pe.payment.PaymentRepository;
import com.academy.fintech.pe.payment.PaymentStatus;
import com.academy.fintech.pe.payment_schedule.PaymentSchedule;
import com.academy.fintech.pe.payment_schedule.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PaymentScheduler {
    @Autowired
    private AgreementService agreementService;
    @Autowired
    private PaymentScheduleService paymentScheduleService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BalanceService balanceService;
    @Scheduled(cron = "0 0 0 * * *")
    public void handlePayment(){
        Date payDate = new Date();
        for(Agreement agreement : agreementService.getActiveAgreements()){
            PaymentSchedule schedule = paymentScheduleService.getPaymentSchedule(agreement.getId());
            List<Payment> payments = paymentRepository.findByScheduleIdAndStatus(schedule.getId(), PaymentStatus.FUTURE);
            List<Payment> unhandledPayments = payments.stream().filter(val -> val.getPayment_date().before(payDate)).toList();
            Balance balance = balanceService.getBalanceByAgreementId(agreement.getId());
            for(Payment payment : unhandledPayments){
                   
            }
        }
    }
}
