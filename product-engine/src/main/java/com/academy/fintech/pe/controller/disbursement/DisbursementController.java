package com.academy.fintech.pe.controller.disbursement;

import com.academy.fintech.pe.agreement.Agreement;
import com.academy.fintech.pe.agreement.AgreementService;
import com.academy.fintech.pe.agreement.AgreementStatus;
import com.academy.fintech.pe.payment_schedule.PaymentSchedule;
import com.academy.fintech.pe.payment_schedule.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DisbursementController {
    @Autowired
    private AgreementService agreementService;

    @Autowired
    private PaymentScheduleService paymentScheduleService;

    @PostMapping("/disbursement")
    public DisbursementResponse handleDisbursement(@RequestBody DisbursementRequest request) {
        Optional<Agreement> tempAgreement = agreementService.getAgreement(request.getAgreement_id());
        if (tempAgreement.isEmpty()) {
            return new DisbursementResponse("No such agreement");
        }
        Agreement agreement = tempAgreement.get();
        if (agreement.getStatus() != AgreementStatus.NEW) {
            return new DisbursementResponse("Already activated");
        }
        PaymentSchedule schedule = paymentScheduleService.createSchedule(agreement, request.getDisbursement_date());
        agreementService.activateAgreement(request.getAgreement_id(), request.getDisbursement_date());
        return new DisbursementResponse(schedule.getId());
    }

}
