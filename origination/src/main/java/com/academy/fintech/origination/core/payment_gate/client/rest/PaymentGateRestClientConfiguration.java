package com.academy.fintech.origination.core.payment_gate.client.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api.client.origination.grpc")
public record PaymentGateRestClientConfiguration(String host, int port) { }
