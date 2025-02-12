package com.academy.fintech.origination.core.service.application;

import com.academy.fintech.application.ApplicationRequest;
import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.core.db.application.ApplicationRepository;
import com.academy.fintech.origination.core.db.application.ApplicationStatus;
import com.academy.fintech.origination.core.db.client.Client;
import com.academy.fintech.origination.core.integration.pe.service.ProductEngineAgreementService;
import com.academy.fintech.origination.exporter.ExporterService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ApplicationService {

    public final static int DUPLICATE_TIMEOUT_MINUTES = 1;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ExporterService exporterService;

    @Autowired
    private ProductEngineAgreementService productEngineAgreementService;


    /**
     * Searches for application with {@code status = NEW} and same disbursement amount.
     * If this application was created for longer than {@code DUPLICATE_TIMEOUT_MINUTES}, it is considered as duplicate.
     */
    public Optional<Integer> checkDuplicate(Application application, Client client) {
        Date currentTime = new Date();
        log.info(currentTime.toString());
        List<Application> applicationList = applicationRepository.findByClientIdAndRequestedDisbursementAmountAndStatus(client.getId(), application.getRequestedDisbursementAmount(), ApplicationStatus.NEW);
        for (Application value : applicationList) {
            log.info(value.getCreationTime().toString());
            long diffMillis = Math.abs(value.getCreationTime().getTime() - currentTime.getTime());
            long diffMinutes = TimeUnit.MINUTES.convert(diffMillis, TimeUnit.MILLISECONDS);
            log.info("{}, {}", diffMinutes, diffMillis);
            if (diffMinutes <= DUPLICATE_TIMEOUT_MINUTES) {
                return Optional.of(value.getId());
            }
        }
        return Optional.empty();
    }

    /**
     * Created application for provided {@code client}.
     * If application is considered duplicate, then returns original application id.
     */
    @Transactional
    public Application createApplication(ApplicationRequest request, Client client) throws DuplicateApplicationException {
        Application applicationProto = Application.builder()
                .clientId(client.getId())
                .status(ApplicationStatus.NEW)
                .requestedDisbursementAmount(new BigDecimal(request.getDisbursementAmount()))
                .creationTime(new Date())
                .build();
        Optional<Integer> duplicateId = checkDuplicate(applicationProto, client);
        if (duplicateId.isPresent()) {
            throw new DuplicateApplicationException(duplicateId.get());
        }

        Application application = applicationRepository.save(applicationProto);
        exporterService.exportApplication(application);
        return application;
    }

    @Transactional
    public boolean cancelApplication(int applicationId) {
        try {
            Application application = applicationRepository.getReferenceById(applicationId);
            application.setStatus(ApplicationStatus.CLOSED);
            applicationRepository.deleteById(applicationId);
            exporterService.exportApplication(application);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Application> getNewApplications() {
        return applicationRepository.findByStatus(ApplicationStatus.NEW);
    }

    @Transactional
    public void setApplicationStatus(Application application, ApplicationStatus applicationStatus) {
        application.setStatus(applicationStatus);
        applicationRepository.save(application);
        exporterService.exportApplication(application);
    }

    public void markScoring(Application application) {
        setApplicationStatus(application, ApplicationStatus.SCORING);
    }

    public void acceptApplication(Application application) {
        setApplicationStatus(application, ApplicationStatus.ACCEPTED);
        productEngineAgreementService.createAgreement(application);
    }

    public void rejectApplication(Application application) {
        setApplicationStatus(application, ApplicationStatus.CLOSED);
    }
}
