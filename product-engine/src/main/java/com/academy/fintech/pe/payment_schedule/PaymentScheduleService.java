package com.academy.fintech.pe.payment_schedule;

import com.academy.fintech.pe.agreement.Agreement;
import com.academy.fintech.pe.payment.Payment;
import com.academy.fintech.pe.payment.PaymentRepository;
import com.academy.fintech.pe.payment.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.academy.fintech.pe.Util.nextMonth;

@Service
public class PaymentScheduleService {
    @Autowired
    private PaymentScheduleRepository paymentScheduleRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    private static BigDecimal calcPMT(BigDecimal interest, int loan_term, BigDecimal pv, BigDecimal fv) {
        return interest.divide(
                        interest.add(BigDecimal.ONE)
                                .pow(loan_term)
                                .subtract(BigDecimal.ONE), 10, RoundingMode.HALF_EVEN)
                .multiply(
                        pv.multiply(
                                        interest.add(
                                                BigDecimal.ONE).pow(loan_term)
                                )
                                .add(fv)
                                .negate()
                );

    }

    private static BigDecimal calcFV(BigDecimal interest, int loan_term, BigDecimal c, BigDecimal pv) {
        return (interest.add(BigDecimal.ONE)
                .pow(loan_term)
                .subtract(BigDecimal.ONE)
                .divide(interest, 10, RoundingMode.HALF_EVEN)
                .multiply(c)
                .add(
                        pv.multiply(
                                interest.add(BigDecimal.ONE)
                                        .pow(loan_term)
                        )
                )
        ).negate();
    }

    private static BigDecimal calcPPMT(BigDecimal interest, int per, int loan_term, BigDecimal pv,
                                       BigDecimal fv) {
        return calcPMT(interest, loan_term, pv, fv).subtract(calcIPMT(interest, per, loan_term, pv, fv));
    }

    private static BigDecimal calcIPMT(BigDecimal interest, int per, int loan_term, BigDecimal pv,
                                       BigDecimal fv) {
        return calcFV(interest, per - 1, calcPMT(interest, loan_term, pv, fv), pv).multiply(interest);
    }


    public List<Payment> createSchedulePayments(int loan_term, BigDecimal principalAmount, BigDecimal interest, Date initialDate) {
        BigDecimal rate = interest.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_EVEN);
        Date currentDate = initialDate;
        List<Payment> result = new ArrayList<>();
        for (int i = 0; i < loan_term; i++) {
            currentDate = nextMonth(currentDate);
            Payment payment = Payment.builder()
                    .period_payment(calcPMT(rate, loan_term, principalAmount, BigDecimal.valueOf(0)))
                    .interest_payment(calcIPMT(rate, i + 1, loan_term, principalAmount, BigDecimal.valueOf(0)))
                    .principal_payment(calcPPMT(rate, i + 1, loan_term, principalAmount, BigDecimal.valueOf(0)))
                    .payment_date(currentDate)
                    .period_number(i + 1)
                    .status(PaymentStatus.FUTURE)
                    .build();
            result.add(payment);
        }
        return result;
    }

    /**
     * Creates schedule object in database.
     * Creates payment objects in database in amount of {@code loan_term} and binds them to schedule.
     *
     * @return Payment schedule object, saved in database
     */
    @Transactional
    public PaymentSchedule createSchedule(Agreement agreement, Date initialDate) {
        PaymentSchedule schedule = PaymentSchedule.builder()
                .agreement(agreement)
                .build();
        paymentScheduleRepository.save(schedule);
        List<Payment> paymentList = createSchedulePayments(agreement.getLoanTerm(),
                agreement.getPrincipalAmount(),
                agreement.getInterest(),
                initialDate);
        for (Payment payment : paymentList) {
            payment.setSchedule(schedule);
            paymentRepository.save(payment);
        }
        return schedule;
    }

    public PaymentSchedule getPaymentSchedule(int agreementId){
        return paymentScheduleRepository.findFirstByAgreementId(agreementId);
    }
}
