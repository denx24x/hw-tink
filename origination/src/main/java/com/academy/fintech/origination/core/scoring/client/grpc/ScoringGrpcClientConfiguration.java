package com.academy.fintech.origination.core.scoring.client.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api.client.origination.grpc")
public record ScoringRestClientConfiguration(String host, int port) { }
