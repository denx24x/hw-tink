package com.academy.fintech.merchantprovider.rest.transfer.v1.dto;

import com.academy.fintech.merchantprovider.db.transfer.TransferType;

import java.math.BigDecimal;

public record TransferRequestDto(
        int clientId,
        BigDecimal amount
){};
