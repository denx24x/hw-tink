package com.academy.fintech.origination.core.integration.pe;

import com.academy.fintech.origination.core.db.application.Application;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Product (
    String productCode,
    String productVersion,
    Integer loanTerm,
    BigDecimal interest,
    BigDecimal originationAmount
){}
