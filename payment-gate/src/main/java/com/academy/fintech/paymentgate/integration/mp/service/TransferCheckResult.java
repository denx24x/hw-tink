package com.academy.fintech.paymentgate.integration.mp.service;

import jakarta.annotation.Nullable;
import lombok.Builder;

import java.util.Date;

@Builder
public record TransferCheckResult(
        boolean finished,
        @Nullable
        Date finishDate
) {
}
