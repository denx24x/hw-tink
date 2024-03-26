package com.academy.fintech.paymentgate.integration.mp.service;

import com.academy.fintech.paymentgate.integration.mp.client.MerchantProviderClientService;
import com.academy.fintech.paymentgate.integration.mp.dto.TransferCheckResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantProviderTransferService {
    @Autowired
    private MerchantProviderClientService merchantProviderClientService;

    public TransferCheckResult checkTransfer(int id) {
        TransferCheckResponseDto responseDto = merchantProviderClientService.checkTransfer(id);
        return TransferCheckResult.builder()
                .finished(responseDto.finished())
                .finishDate(responseDto.finish_date()).build();
    }
}
