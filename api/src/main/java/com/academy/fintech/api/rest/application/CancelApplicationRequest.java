package com.academy.fintech.api.rest.application;

import lombok.Builder;

@Builder
public record CancelApplicationRequest (
        int applicationId
){}
