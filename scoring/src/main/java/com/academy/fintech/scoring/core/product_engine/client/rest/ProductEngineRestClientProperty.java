package com.academy.fintech.scoring.core.product_engine.client.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "scoring.client.product-engine.rest")
public record ProductEngineRestClientProperty(String url) { }