package com.academy.fintech.origination.core.scoring.client.grpc;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "origination.client.scoring.grpc")
public record ScoringGrpcClientConfiguration(String host, int port) { }
