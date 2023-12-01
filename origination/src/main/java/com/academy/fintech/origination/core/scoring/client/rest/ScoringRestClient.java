package com.academy.fintech.origination.core.scoring.client.rest;

import org.springframework.web.client.RestTemplate;

public class ScoringRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final int port;
    private final String host;

    public ScoringRestClient(ScoringRestClientConfiguration property) {
        this.port = property.port();
        this.host = property.host();
    }

    public void createAgreement(){

    }

}
