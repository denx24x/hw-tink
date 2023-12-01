package com.academy.fintech.origination.core.db.client;

import com.academy.fintech.origination.core.db.application.Application;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String email;
    private BigDecimal salary;
    @OneToMany
    @JoinColumn(name="id", referencedColumnName = "client_id", table = "application")
    List<Application> applicationList;
}
