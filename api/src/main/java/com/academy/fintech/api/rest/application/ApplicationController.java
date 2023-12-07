package com.academy.fintech.api.rest.application;

import com.academy.fintech.api.core.application.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;

    @PostMapping
    public int create(@RequestBody ApplicationRequest applicationRequest) {
        return applicationService.createApplication(applicationMapper.mapRequestToDto(applicationRequest));
    }

    @DeleteMapping
    public boolean cancel(@RequestBody CancelApplicationRequest cancelApplicationRequest){
        return applicationService.cancelApplication(applicationMapper.mapRequestToDto(cancelApplicationRequest));
    }

}
