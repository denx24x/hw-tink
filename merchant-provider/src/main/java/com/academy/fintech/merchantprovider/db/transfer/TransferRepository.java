package com.academy.fintech.merchantprovider.db.transfer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    public Transfer findById(int id);
}
