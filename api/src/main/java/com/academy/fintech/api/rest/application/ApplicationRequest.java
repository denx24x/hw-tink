package com.academy.fintech.api.rest.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ApplicationRequest(
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        String email,
        BigDecimal salary,
        BigDecimal amount
) { }
