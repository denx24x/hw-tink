package com.academy.fintech.api.public_interface.application.dto;

import lombok.Builder;

@Builder
public record CancelApplicationDto(
        int applicationId
) {
}
