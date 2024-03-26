package com.academy.fintech.origination.configuration;

import com.academy.fintech.origination.core.integration.scoring.grpc.ScoringGrpcClientProperty;
import com.academy.fintech.origination.configuration.product.ProductProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ProductProperty.class, ScoringGrpcClientProperty.class})
public class PropertiesConfiguration {
}
