package com.academy.fintech.paymentgate.scheduler.mp;

import com.academy.fintech.paymentgate.db.transfer.Transfer;
import com.academy.fintech.paymentgate.db.transfer.TransferService;
import com.academy.fintech.paymentgate.db.transfer.disbursement.DisbursementTransfer;
import com.academy.fintech.paymentgate.db.transfer.payment.PaymentTransfer;
import com.academy.fintech.paymentgate.integration.mp.service.MerchantProviderTransferService;
import com.academy.fintech.paymentgate.integration.mp.service.TransferCheckResult;
import com.academy.fintech.paymentgate.integration.pe.service.ProductEngineNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class TransferCheckScheduler {
    Logger logger = LoggerFactory.getLogger(TransferCheckScheduler.class);
    @Autowired
    private TransferService transferService;

    @Autowired
    private MerchantProviderTransferService merchantProviderTransferService;

    @Autowired
    private ProductEngineNotificationService productEngineNotificationService;

    @Scheduled(fixedDelay = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public void checkUnfinishedTransfers() {
        logger.info("tracking");
        List<Transfer> unfinishedTransfers = transferService.getUnfinishedTransfers();
        for (Transfer transfer : unfinishedTransfers) {
            TransferCheckResult transferCheckResult = merchantProviderTransferService.checkTransfer(transfer.getTransferId());
            if (transferCheckResult.finished()) {
                transferService.markTransferFinished(transfer, transferCheckResult.finishDate());
                if (transfer instanceof DisbursementTransfer disbursementTransfer) {
                    logger.info("notify disbursement");
                    productEngineNotificationService.notifyDisbursementFinished(disbursementTransfer.getAgreementId(), transferCheckResult.finishDate());
                } else if (transfer instanceof PaymentTransfer paymentTransfer) {
                    logger.info("notify payment");
                    productEngineNotificationService.notifyPayment(paymentTransfer.getBalanceId(), paymentTransfer.getAmount(), transferCheckResult.finishDate());
                } else {
                    throw new RuntimeException("Unknown transfer type");
                }
            }
        }
    }

}
