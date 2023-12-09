package com.academy.fintech.origination.property;

import java.math.BigDecimal;

public record ProductProperty (String code, String version, BigDecimal origination_amount, BigDecimal interest, int loan_term)  {
}
