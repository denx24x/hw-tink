package com.academy.fintech.api.core.origination.client;

import com.academy.fintech.api.core.origination.client.grpc.OriginationGrpcClient;
import com.academy.fintech.api.public_interface.application.dto.ApplicationDto;
import com.academy.fintech.api.public_interface.application.dto.CancelApplicationDto;
import com.academy.fintech.application.ApplicationRequest;
import com.academy.fintech.application.ApplicationResponse;
import com.academy.fintech.application.CancelApplicationRequest;
import com.academy.fintech.application.CancelApplicationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OriginationClientService {

    private final OriginationGrpcClient originationGrpcClient;

    public int createApplication(ApplicationDto applicationDto) {
        ApplicationRequest request = mapDtoToRequest(applicationDto);
        try {
            ApplicationResponse response = originationGrpcClient.createApplication(request);
            return response.getApplicationId();
        }catch (DuplicateApplicationException e){
            log.info(e.getMessage());
            return e.getDuplicateId();
        }

    }

    public boolean cancelApplication(CancelApplicationDto cancelApplicationDto){
        CancelApplicationRequest request = mapDtoToRequest(cancelApplicationDto);
        CancelApplicationResponse response = originationGrpcClient.cancelApplication(request);
        return response.getSuccess();
    }

    private static ApplicationRequest mapDtoToRequest(ApplicationDto applicationDto) {
        return ApplicationRequest.newBuilder()
                .setFirstName(applicationDto.firstName())
                .setLastName(applicationDto.lastName())
                .setEmail(applicationDto.email())
                .setSalary(applicationDto.salary())
                .setDisbursementAmount(applicationDto.amount())
                .build();
    }

    private static CancelApplicationRequest mapDtoToRequest(CancelApplicationDto cancelApplicationDto){
        return CancelApplicationRequest.newBuilder()
                .setApplicationId(cancelApplicationDto.applicationId())
                .build();
    }

}
