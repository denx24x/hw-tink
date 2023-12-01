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

    public final static int DUPLICATE_TIMEOUT_MINUTES = 2;

    @Autowired
    private ApplicationRepository applicationRepository;

    public Optional<Integer> checkDuplicate(Application application, Client client){
        for(Application value : client.getApplicationList()){
            if(Objects.equals(value.getRequested_disbursement_amount(), application.getRequested_disbursement_amount())){
                long diffMillis = Math.abs(value.getCreationTime().getTime() - application.getCreationTime().getTime());
                long diffMinutes = TimeUnit.MINUTES.convert(diffMillis, TimeUnit.MILLISECONDS);
                if(diffMinutes < DUPLICATE_TIMEOUT_MINUTES){
                    return Optional.of(value.getId());
                }
            }
        }
        return Optional.empty();
    }

    public Application createApplication(ApplicationRequest request, Client client){
        Application applicationProto = Application.builder()
                .client_id(client.getId())
                .status(ApplicationStatus.NEW)
                .requested_disbursement_amount(new BigDecimal(request.getDisbursementAmount()))
                .build();
        Optional<Integer> duplicateId = checkDuplicate(applicationProto, client);
        return applicationRepository.save(applicationProto);
    }
}
