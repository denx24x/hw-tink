package com.academy.fintech.merchantprovider.db.transfer;

import com.academy.fintech.merchantprovider.config.transfer.TransferConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Service
public class TransferServiceImpl implements TransferService {


    @Autowired
    private TransferRepository transferRepository;


    @Override
    public Transfer addTransfer(String balanceId, BigDecimal amount, TransferType type, Date finishTime) {
        Transfer result = Transfer.builder()
                .balanceId(balanceId)
                .amount(amount)
                .type(type)
                .finishTime(finishTime)
                .build();
        return transferRepository.save(result);
    }

    @Override
    public Date getTransferFinishTime(int id) {
        return transferRepository.findById(id).getFinishTime();
    }
}
