package com.academy.fintech.origination.core.db.client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Client findClientByEmail(String email);
}
