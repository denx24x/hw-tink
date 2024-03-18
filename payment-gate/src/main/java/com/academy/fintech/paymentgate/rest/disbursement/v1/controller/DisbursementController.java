package com.academy.fintech.paymentgate.rest.disbursement.v1.controller;

import com.academy.fintech.paymentgate.rest.disbursement.v1.dto.DisbursementRequestDto;
import com.academy.fintech.paymentgate.service.disbursement.v1.DisbursementServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DisbursementController {
    @Autowired
    private DisbursementServiceV1 disbursementServiceV1;

    @PostMapping("/disbursement")
    public void makeDisbursement(@RequestBody DisbursementRequestDto request) {
        disbursementServiceV1.makeDisbursement(request.client_balance_id(), request.amount());
    }
}
