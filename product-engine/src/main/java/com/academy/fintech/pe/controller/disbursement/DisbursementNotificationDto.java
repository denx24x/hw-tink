package com.academy.fintech.pe.controller.disbursement;

import lombok.Builder;

import java.util.Date;

@Builder
public record DisbursementNotificationDto(
        int agreeement_id,
        Date finishDate
) {
}
