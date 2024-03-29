package com.academy.fintech.origination.configuration.product;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "product")
public record ProductProperty(String code, String version, BigDecimal origination_amount, BigDecimal interest,
                              int loan_term) {
}
