package com.academy.fintech.pe.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public void markPaymentOverdue(Payment payment){
        payment.setStatus(PaymentStatus.OVERDUE);
        paymentRepository.save(payment);
    }

    public void markPaymentPaid(Payment payment){
        payment.setStatus(PaymentStatus.PAID);
        paymentRepository.save(payment);
    }

    public List<Payment> getFuturePaymentsForSchedule(int scheduleId){
        return paymentRepository.findByScheduleIdAndStatus(scheduleId, PaymentStatus.FUTURE);
    }
}
