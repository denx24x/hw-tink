package com.academy.fintech.pe.controller.creation;

import com.academy.fintech.pe.agreement.AgreementService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

/**
 * Returns agreement id if creation was successful, otherwise returns error message
 */
@Getter
@Setter
@NoArgsConstructor
public class AgreementCreationResponse {
    private boolean success;
    private Optional<String> errorMessage;
    private Optional<Integer> agreementCode;

    public AgreementCreationResponse(String errorMessage){
        this.success = false;
        this.errorMessage = Optional.of(errorMessage);
    }

    public AgreementCreationResponse(Integer agreementCode){
        this.success = true;
        this.agreementCode = Optional.of(agreementCode);
    }
}
