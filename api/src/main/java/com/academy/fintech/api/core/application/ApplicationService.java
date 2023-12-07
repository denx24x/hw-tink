package com.academy.fintech.api.core.application;

import com.academy.fintech.api.core.origination.client.OriginationClientService;
import com.academy.fintech.api.public_interface.application.dto.ApplicationDto;
import com.academy.fintech.api.public_interface.application.dto.CancelApplicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final OriginationClientService originationClientService;

    public int createApplication(ApplicationDto applicationDto) {
        return originationClientService.createApplication(applicationDto);
    }

    public boolean cancelApplication(CancelApplicationDto cancelApplicationDto){
        return originationClientService.cancelApplication(cancelApplicationDto);
    }

}
