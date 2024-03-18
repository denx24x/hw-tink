package com.academy.fintech.merchantprovider.config;

import com.academy.fintech.merchantprovider.config.integration.pg.PaymentGateRestClientProperty;
import com.academy.fintech.merchantprovider.config.transfer.TransferConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({TransferConfig.class, PaymentGateRestClientProperty.class})
public class PropertiesConfiguration {
}
