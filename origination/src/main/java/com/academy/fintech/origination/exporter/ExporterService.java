package com.academy.fintech.origination.exporter;

import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.exporter.db.ExporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExporterService {
    @Autowired
    private ExporterRepository exporterRepository;

    public void exportApplication(Application application){
        exporterRepository.insert(application.getId(), application.getStatus().toString());
    }
}
