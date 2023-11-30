package com.academy.fintech.pe.controller.disbursement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * Returns schedule id if creation was successful, otherwise returns error message
 */
@Setter
@Getter
@AllArgsConstructor
public class DisbursementResponse {
    boolean success;
    Optional<String> errorMessage;
    Optional<Integer> scheduleId;

    public DisbursementResponse(String errorMessage){
        this.success = false;
        this.errorMessage = Optional.of(errorMessage);
    }

    public DisbursementResponse(Integer agreementCode){
        this.success = true;
        this.scheduleId = Optional.of(agreementCode);
    }
}
