package com.academy.fintech.paymentgate.service.disbursement.v1;

import com.academy.fintech.paymentgate.db.transfer.disbursement.DisbursementTransferService;
import com.academy.fintech.paymentgate.integration.mp.service.MerchantProviderDisbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DisbursementServiceV1Impl implements DisbursementServiceV1 {
    @Autowired
    private MerchantProviderDisbursementService disbursementIntegrationService;

    @Autowired
    private DisbursementTransferService disbursementTransferService;

    @Override
    public void makeDisbursement(String balanceId, int agreementId, BigDecimal amount) {
        Integer transferId = disbursementIntegrationService.makeDisbursement(balanceId, amount);
        disbursementTransferService.startDisbursementTracking(transferId, agreementId);
    }
}
