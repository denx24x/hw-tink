package com.academy.fintech.origination.core.product_engine.client.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api.client.origination.grpc")
public record ProductEngineRestClientConfiguration(String host, int port) { }
