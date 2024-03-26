package com.academy.fintech.origination.core.scheduler;

import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.core.integration.scoring.ScoringClientService;
import com.academy.fintech.origination.core.service.application.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class ScoringScheduler {
    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ScoringClientService scoringClientService;

    @Transactional
    private void processScoring(Application application) {
        applicationService.markScoring(application);
        BigDecimal score = scoringClientService.requestScoring(application, application.getClient());
        if (score.compareTo(BigDecimal.ZERO) > 0) {
            applicationService.rejectApplication(application);
        } else {
            applicationService.acceptApplication(application);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void requestScoring() {
        List<Application> applicationList = applicationService.getNewApplications();
        for (Application application : applicationList) {
            processScoring(application);
        }
    }
}
