package com.academy.fintech.api.core.origination.client;

import com.academy.fintech.api.core.origination.client.grpc.OriginationGrpcClient;
import com.academy.fintech.api.public_interface.application.dto.ApplicationDto;
import com.academy.fintech.application.ApplicationRequest;
import com.academy.fintech.application.ApplicationResponse;
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

    private static ApplicationRequest mapDtoToRequest(ApplicationDto applicationDto) {
        return ApplicationRequest.newBuilder()
                .setFirstName(applicationDto.firstName())
                .setLastName(applicationDto.lastName())
                .setEmail(applicationDto.email())
                .setSalary(applicationDto.salary())
                .setDisbursementAmount(applicationDto.amount())
                .build();
    }

}
