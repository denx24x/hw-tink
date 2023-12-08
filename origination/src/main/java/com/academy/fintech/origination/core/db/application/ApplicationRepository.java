package com.academy.fintech.origination.core.db.application;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    List<Application> findByClientIdAndRequestedDisbursementAmountAndStatus(int clientId, BigDecimal requestedDisbursementAmount, ApplicationStatus status);

    List<Application> findByStatus(ApplicationStatus status);
}
