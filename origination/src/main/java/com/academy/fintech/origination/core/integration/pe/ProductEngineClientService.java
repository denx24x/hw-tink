package com.academy.fintech.origination.core.integration.pe;

import com.academy.fintech.origination.core.integration.pe.rest.ProductEngineRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductEngineClientService {
    @Autowired
    private ProductEngineRestClient productEngineRestClient;
}
