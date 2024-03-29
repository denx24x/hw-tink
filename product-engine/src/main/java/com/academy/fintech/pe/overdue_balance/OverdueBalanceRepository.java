package com.academy.fintech.pe.overdue_balance;

import com.academy.fintech.pe.balance.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverdueBalanceRepository extends JpaRepository<OverdueBalance, Integer> {
    public Balance findFirstByAgreementId(int agreementId);
}
