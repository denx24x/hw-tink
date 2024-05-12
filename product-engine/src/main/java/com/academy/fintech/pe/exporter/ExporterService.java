package com.academy.fintech.pe.exporter;

import com.academy.fintech.pe.agreement.Agreement;
import com.academy.fintech.pe.exporter.db.ExporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExporterService {
    @Autowired
    private ExporterRepository exporterRepository;

    public void exportAgreement(Agreement agreement){
        exporterRepository.insert(agreement.getId(), agreement.getStatus().toString(), agreement.getDisbursementDate(), agreement.getNextPaymentDate());
    }
}
