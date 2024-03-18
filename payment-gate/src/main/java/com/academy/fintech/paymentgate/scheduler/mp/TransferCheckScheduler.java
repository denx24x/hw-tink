package com.academy.fintech.paymentgate.scheduler.mp;

import com.academy.fintech.paymentgate.db.transfer.Transfer;
import com.academy.fintech.paymentgate.db.transfer.TransferService;
import com.academy.fintech.paymentgate.integration.mp.service.MerchantProviderTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransferCheckScheduler {
    @Autowired
    private TransferService transferService;

    @Autowired
    private MerchantProviderTransferService merchantProviderTransferService;

    @Scheduled(fixedDelay = 1000)
    public void checkUnfinishedTransfers(){
        List<Transfer> unfinishedTransfers = transferService.getUnfinishedTransfers();
        for(Transfer transfer : unfinishedTransfers){
            if(merchantProviderTransferService.checkTransfer(transfer.getTransferId())){
                transferService.markTransferFinished(transfer);
            }
        }
    }
}
