package com.academy.fintech.merchantprovider.config.integration.pg;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration.payment-gate.client.rest")
public record PaymentGateRestClientProperty(String url) {
}