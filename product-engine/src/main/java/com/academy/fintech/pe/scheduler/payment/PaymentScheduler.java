package com.academy.fintech.pe.scheduler.payment;

import com.academy.fintech.pe.agreement.Agreement;
import com.academy.fintech.pe.agreement.AgreementService;
import com.academy.fintech.pe.balance.Balance;
import com.academy.fintech.pe.balance.BalanceService;
import com.academy.fintech.pe.overdue_balance.OverdueBalance;
import com.academy.fintech.pe.overdue_balance.OverdueBalanceService;
import com.academy.fintech.pe.payment.Payment;
import com.academy.fintech.pe.payment.PaymentService;
import com.academy.fintech.pe.payment_schedule.PaymentSchedule;
import com.academy.fintech.pe.payment_schedule.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

// TODO: refactor
@Component
public class PaymentScheduler {
    @Autowired
    private AgreementService agreementService;
    @Autowired
    private PaymentScheduleService paymentScheduleService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BalanceService balanceService;

    @Autowired
    private OverdueBalanceService overdueBalanceService;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void handlePayment() {
        Date payDate = new Date();
        for (Agreement agreement : agreementService.getActiveAgreements()) {
            PaymentSchedule schedule = paymentScheduleService.getPaymentSchedule(agreement.getId());
            List<Payment> payments = paymentService.getFuturePaymentsForSchedule(schedule.getId());
            List<Payment> unhandledPayments = payments.stream().filter(val -> val.getPayment_date().before(payDate)).toList();
            Balance balance = balanceService.getBalanceByAgreementId(agreement.getId());
            OverdueBalance overdueBalance = overdueBalanceService.getBalanceForAgreement(agreement.getId());
            for (Payment payment : unhandledPayments) {
                if (payment.getPeriod_payment().compareTo(balance.getBalance()) <= 0) {
                    balanceService.applyAgreementPayment(balance, payment.getPeriod_payment());
                    paymentService.markPaymentPaid(payment);
                } else {
                    overdueBalanceService.applyOverdue(overdueBalance, payment.getPeriod_payment());
                    paymentService.markPaymentOverdue(payment);
                    // notify overdue
                }
            }
        }
    }
}
