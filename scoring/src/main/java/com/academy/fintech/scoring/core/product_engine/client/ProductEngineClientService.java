package com.academy.fintech.scoring.core.product_engine.client;

import com.academy.fintech.scoring.core.product_engine.client.rest.ProductEngineRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductEngineClientService {
    @Autowired
    private ProductEngineRestClient productEngineRestClient;

    public boolean hasCredit(long clientId){
        return productEngineRestClient.hasCredit(clientId);
    }

    public long getMaxOverdue(long clientId){
        return productEngineRestClient.getMaxOverdue(clientId);
    }
}
