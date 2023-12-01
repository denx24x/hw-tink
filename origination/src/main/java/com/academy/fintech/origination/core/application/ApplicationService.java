package com.academy.fintech.origination.core.application;

import com.academy.fintech.application.ApplicationRequest;
import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.core.db.application.ApplicationRepository;
import com.academy.fintech.origination.core.db.application.ApplicationStatus;
import com.academy.fintech.origination.core.db.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ApplicationService {

    public final static int DUPLICATE_TIMEOUT_MINUTES = 1;

    @Autowired
    private ApplicationRepository applicationRepository;

    /**
     * Searches for application with {@code status = NEW} and same disbursement amount.
     * If this application was created for longer than {@code DUPLICATE_TIMEOUT_MINUTES}, it is considered as duplicate.
     */
    public Optional<Integer> checkDuplicate(Application application, Client client) {
        for (Application value : client.getApplicationList()) {
            if (Objects.equals(value.getRequested_disbursement_amount(), application.getRequested_disbursement_amount())
                    && value.getStatus() == ApplicationStatus.NEW) {
                long diffMillis = Math.abs(value.getCreationTime().getTime() - application.getCreationTime().getTime());
                long diffMinutes = TimeUnit.MINUTES.convert(diffMillis, TimeUnit.MILLISECONDS);
                if (diffMinutes < DUPLICATE_TIMEOUT_MINUTES) {
                    return Optional.of(value.getId());
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Created application for provided {@code client}.
     * If application is considered duplicate, then returns original application id.
     */
    public Application createApplication(ApplicationRequest request, Client client) throws DuplicateApplicationException {
        Application applicationProto = Application.builder()
                .client_id(client.getId())
                .status(ApplicationStatus.NEW)
                .requested_disbursement_amount(new BigDecimal(request.getDisbursementAmount()))
                .build();
        Optional<Integer> duplicateId = checkDuplicate(applicationProto, client);
        if (duplicateId.isPresent()) {
            throw new DuplicateApplicationException(duplicateId.get());
        }
        return applicationRepository.save(applicationProto);
    }

    public boolean cancelApplication(int applicationId){
        try {
            applicationRepository.deleteById(applicationId);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
