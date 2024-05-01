package com.academy.fintech.paymentgate.config;

import com.academy.fintech.paymentgate.config.integration.mp.MerchantProviderRestClientProperty;
import com.academy.fintech.paymentgate.config.integration.pe.ProductEngineRestClientProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({MerchantProviderRestClientProperty.class, ProductEngineRestClientProperty.class})
public class PropertiesConfiguration {
}
