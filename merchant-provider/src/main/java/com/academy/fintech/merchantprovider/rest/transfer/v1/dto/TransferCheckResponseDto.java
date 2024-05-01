package com.academy.fintech.merchantprovider.rest.transfer.v1.dto;

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
