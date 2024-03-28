package com.academy.fintech.pe.agreement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
    public List<Agreement> findByClientIdAndStatus(long clientId, AgreementStatus status);

    public boolean existsByClientIdAndStatus(long clientId, AgreementStatus status);

    public List<Agreement> findByStatus(AgreementStatus status);
}
