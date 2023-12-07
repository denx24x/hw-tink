package com.academy.fintech.api.rest.application;

import com.academy.fintech.api.public_interface.application.dto.ApplicationDto;
import com.academy.fintech.api.public_interface.application.dto.CancelApplicationDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ApplicationMapper {

    public ApplicationDto mapRequestToDto(ApplicationRequest request) {
        return ApplicationDto.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .amount(request.amount().toString())
                .salary(request.salary().toString())
                .build();
    }

    public CancelApplicationDto mapRequestToDto(CancelApplicationRequest request){
        return CancelApplicationDto.builder()
                .applicationId(request.applicationId())
                .build();
    }

}
