package com.academy.fintech.pe.balance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    public Balance getById(String balanceId);

    public Balance findFirstByAgreementId(int agreementId);
}
