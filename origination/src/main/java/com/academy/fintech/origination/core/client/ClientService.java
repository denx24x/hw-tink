package com.academy.fintech.origination.core.client;

import com.academy.fintech.application.ApplicationRequest;
import com.academy.fintech.origination.core.db.client.Client;
import com.academy.fintech.origination.core.db.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Creates client using provided {@code request}.
     * If client with such email already exists, returns this client.
     */
    public Client getClient(ApplicationRequest request) {
        Client client = clientRepository.findClientByEmail(request.getEmail());
        if (client == null) {
            client = clientRepository.save(Client.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .salary(new BigDecimal(request.getSalary()))
                    .build());
        }
        return client;
    }
}
