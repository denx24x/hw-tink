package com.academy.fintech.pe.controller.creation;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgreementCreationRequest implements Serializable {
    int client_id;
    String product_code;
    String product_version;
    int loan_term;
    BigDecimal disbursement_amount;
    BigDecimal interest;
    BigDecimal origination_amount;

    public BigDecimal calcPrincipalAmount(){
        return disbursement_amount.add(origination_amount);
    }
}
