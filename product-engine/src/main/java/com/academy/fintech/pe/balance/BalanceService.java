package com.academy.fintech.pe.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;

    public void applyPayment(String balanceId, BigDecimal amount) {
        Balance balance = balanceRepository.getById(balanceId);
        balance.balance = balance.balance.add(amount);
        balanceRepository.save(balance);
    }

    public void applyAgreementPayment(Balance balance, BigDecimal amount) {
        balance.setBalance(balance.getBalance().add(amount.negate()));
        balanceRepository.save(balance);
    }

    public String createBalance(int id) {
        Balance balance = Balance.builder()
                .agreementId(id)
                .balance(BigDecimal.ZERO)
                .build();
        return balanceRepository.save(balance).id;
    }

    public Balance getBalanceByAgreementId(int agreementid) {
        return balanceRepository.findFirstByAgreementId(agreementid);
    }
}
