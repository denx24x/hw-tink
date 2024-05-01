package com.academy.fintech.origination.core.integration.pe;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Product(
        String productCode,
        String productVersion,
        Integer loanTerm,
        BigDecimal interest,
        BigDecimal originationAmount
) {
}
