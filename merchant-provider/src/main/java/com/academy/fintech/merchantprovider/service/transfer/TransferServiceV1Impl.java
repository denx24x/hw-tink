package com.academy.fintech.merchantprovider.service.transfer;

import com.academy.fintech.merchantprovider.config.transfer.TransferConfig;
import com.academy.fintech.merchantprovider.db.transfer.Transfer;
import com.academy.fintech.merchantprovider.db.transfer.TransferService;
import com.academy.fintech.merchantprovider.db.transfer.TransferType;
import com.academy.fintech.merchantprovider.integration.pg.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Service
public class TransferServiceV1Impl implements TransferServiceV1 {
    @Autowired
    private TransferService transferService;
    @Autowired
    private NotificationService notificationService;
    private final Random random = new Random();
    private final long SECOND = 1000;
    private final int maxDelay;
    public TransferServiceV1Impl(TransferConfig transferConfig) {
        maxDelay = transferConfig.maxDelay();
    }

    public Date generateFinishTime() {
        Date finishTime = new Date();
        finishTime.setTime(finishTime.getTime() + random.nextInt(this.maxDelay) * SECOND);
        return finishTime;
    }
    @Override
    public int register(int clientId, BigDecimal amount, TransferType type) {
        Transfer result = transferService.addTransfer(clientId, amount, type, generateFinishTime());
        if(type == TransferType.PAYMENT){
            notificationService.notifyPayment(result);
        }
        return result.getId();
    }

    @Override
    public boolean checkTransfer(int id) {
        return transferService.getTransferFinishTime(id).compareTo(new Date()) <= 0;
    }
}
