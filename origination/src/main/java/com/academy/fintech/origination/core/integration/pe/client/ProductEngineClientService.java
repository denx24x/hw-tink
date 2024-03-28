package com.academy.fintech.origination.core.integration.pe.client;

import com.academy.fintech.origination.configuration.product.ProductProperty;
import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.core.integration.pe.Product;
import com.academy.fintech.origination.core.integration.pe.client.rest.ProductEngineRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductEngineClientService {
    @Autowired
    private ProductEngineRestClient productEngineRestClient;

    public void createAgreement(Application application, Product product){
        productEngineRestClient.createAgreement(application, product);
    }
}
