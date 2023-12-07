package com.academy.fintech.scoring.core.origination.client.grpc;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api.client.origination.grpc")
public record OriginationGrpcClientProperty(String host, int port) {
}
