package com.academy.fintech.origination.configuration.integration.scoring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration.scoring.grpc")
public record ScoringGrpcClientProperty(String host, int port) {
}
