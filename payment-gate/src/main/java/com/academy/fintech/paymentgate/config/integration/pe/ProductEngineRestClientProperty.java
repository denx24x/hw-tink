package com.academy.fintech.paymentgate.config.integration.pe;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration.product-engine.client.rest")
public record ProductEngineRestClientProperty(String url) {
}