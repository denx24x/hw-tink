package com.academy.fintech.merchantprovider.rest.transfer.v1.dto;

import java.math.BigDecimal;

public record TransferRequestDto(
        String balance_id,
        BigDecimal amount
){};
