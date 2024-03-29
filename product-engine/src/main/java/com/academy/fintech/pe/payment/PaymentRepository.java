package com.academy.fintech.pe.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    public List<Payment> findByScheduleIdAndStatus(int scheduleId, PaymentStatus status);
}
