package com.academy.fintech.merchantprovider.config.transfer;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "transfer")
public record TransferConfig(
        /**
         * Maximum transfer delay in seconds
         */
        int maxDelay
) {
}
