package com.academy.fintech.pe.balance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    public Balance findByBalanceId(String balanceId);
}
