package com.academy.fintech.paymentgate.integration.mp.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;

import java.util.Date;

@Builder
public record TransferCheckResponseDto(
        boolean finished,
        @Nullable
        Date finish_date
) {
}
