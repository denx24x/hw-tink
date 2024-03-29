package com.academy.fintech.pe.overdue_balance;

import com.academy.fintech.pe.balance.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OverdueBalanceService {
    @Autowired
    private OverdueBalanceRepository overdueBalanceRepository;
    public void applyOverdue(int balanceId, BigDecimal amount){
        OverdueBalance balance = overdueBalanceRepository.getReferenceById(balanceId);
        balance.setBalance(balance.balance.add(amount.negate()));
        overdueBalanceRepository.save(balance);
    }
}
