package com.academy.fintech.origination.core.db.application;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    private int id;
    private int client_id;
    private BigDecimal requested_disbursement_amount;
    private ApplicationStatus status;
    @CreatedDate
    @Column(name = "creation_time")
    private Date creationTime;

}
