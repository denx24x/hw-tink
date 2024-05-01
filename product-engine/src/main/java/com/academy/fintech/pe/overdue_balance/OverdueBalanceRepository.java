package com.academy.fintech.pe.overdue_balance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverdueBalanceRepository extends JpaRepository<OverdueBalance, Integer> {
    public OverdueBalance findFirstByAgreementId(int agreementId);
}
