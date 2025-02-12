package com.academy.fintech.origination.core.service.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicateApplicationException extends Exception {
    private final int duplicateId;
}
