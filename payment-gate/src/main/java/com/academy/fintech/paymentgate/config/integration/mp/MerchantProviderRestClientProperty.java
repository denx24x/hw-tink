package com.academy.fintech.paymentgate.config.integration.mp;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration.merchant-provider.client.rest")
public record MerchantProviderRestClientProperty(String url) {
}