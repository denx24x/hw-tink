package com.academy.fintech.pe.overdue_balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OverdueBalanceService {
    @Autowired
    private OverdueBalanceRepository overdueBalanceRepository;

    public void applyOverdue(OverdueBalance balance, BigDecimal amount) {
        balance.setBalance(balance.getBalance().add(amount.negate()));
        overdueBalanceRepository.save(balance);
    }

    public OverdueBalance getBalanceForAgreement(int agreementId) {
        return overdueBalanceRepository.findFirstByAgreementId(agreementId);
    }
}
