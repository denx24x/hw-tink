package com.academy.fintech.pe.controller.disbursement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DisbursementRequest {
    private int agreement_id;
    private Date disbursement_date;
}
