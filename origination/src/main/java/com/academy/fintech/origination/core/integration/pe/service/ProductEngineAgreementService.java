package com.academy.fintech.origination.core.integration.pe.service;

import com.academy.fintech.origination.configuration.product.ProductProperty;
import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.core.integration.pe.Product;
import com.academy.fintech.origination.core.integration.pe.client.ProductEngineClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductEngineAgreementService {
    @Autowired
    private ProductEngineClientService productEngineClientService;

    private ProductProperty productProperty;

    public ProductEngineAgreementService(ProductProperty productProperty) {
        this.productProperty = productProperty;
    }

    public void createAgreement(Application application) {
        productEngineClientService.createAgreement(application,
                Product.builder()
                        .loanTerm(productProperty.loan_term())
                        .productCode(productProperty.code())
                        .productVersion(productProperty.version())
                        .originationAmount(productProperty.origination_amount())
                        .interest(productProperty.interest())
                        .build()
        );
    }
}
