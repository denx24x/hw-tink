package com.academy.fintech.origination.core.db.application;

import com.academy.fintech.origination.core.db.client.Client;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="client_id")
    private int clientId;

    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name="requested_disbursement_amount")
    private BigDecimal requestedDisbursementAmount;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(name = "creation_time")
    private Date creationTime;

}
