package com.academy.fintech.paymentgate.rest.payment.v1.controller;

import com.academy.fintech.paymentgate.rest.payment.v1.dto.PaymentRequestDto;
import com.academy.fintech.paymentgate.service.payment.v1.PaymentServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentServiceV1 paymentService;

    @PostMapping("/payment")
    public void paymentNotification(@RequestBody PaymentRequestDto request) {
        paymentService.registerPayment(request.id(), request.balance_id(), request.amount());
    }
}
