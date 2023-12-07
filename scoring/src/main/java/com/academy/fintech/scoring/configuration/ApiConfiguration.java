package com.academy.fintech.scoring.configuration;

import com.academy.fintech.scoring.core.origination.client.grpc.OriginationGrpcClientProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ OriginationGrpcClientProperty.class })
public class ApiConfiguration { }
