package com.academy.fintech.api.core.origination.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DuplicateApplicationException extends Exception {
    private final int duplicateId;
}
