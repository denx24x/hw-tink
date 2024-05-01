package com.academy.fintech.merchantprovider.rest.transfer.v1.controller;

import com.academy.fintech.merchantprovider.db.transfer.TransferType;
import com.academy.fintech.merchantprovider.rest.transfer.v1.dto.TransferCheckResponseDto;
import com.academy.fintech.merchantprovider.rest.transfer.v1.dto.TransferRequestDto;
import com.academy.fintech.merchantprovider.service.transfer.v1.TransferServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransferController {

    @Autowired
    private TransferServiceV1 transferService;

    @PostMapping("/disbursement")
    public int disbursement(@RequestBody TransferRequestDto request) {
        return transferService.register(request.balance_id(), request.amount(), TransferType.DISBURSEMENT);
    }

    @PostMapping("/payment")
    public int payment(@RequestBody TransferRequestDto request) {
        return transferService.register(request.balance_id(), request.amount(), TransferType.PAYMENT);
    }

    @GetMapping("/check_transfer")
    public TransferCheckResponseDto checkTransfer(@RequestParam(name = "id") int id) {
        boolean result = transferService.checkTransfer(id);
        return TransferCheckResponseDto.builder()
                .finished(result)
                .finish_date(result ? transferService.getTransferFinishTime(id) : null)
                .build();
    }
}
