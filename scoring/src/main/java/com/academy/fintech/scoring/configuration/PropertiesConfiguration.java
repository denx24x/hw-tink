package com.academy.fintech.scoring.configuration;

import com.academy.fintech.scoring.core.product_engine.client.rest.ProductEngineRestClientProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ ProductEngineRestClientProperty.class })
public class PropertiesConfiguration { }
