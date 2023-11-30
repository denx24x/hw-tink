package com.academy.fintech.pe.product;

import com.academy.fintech.pe.controller.creation.AgreementCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProduct(String code, String version) {
        return productRepository.findById(new ProductID(code, version));
    }

    public boolean checkProductConstraints(Product product, AgreementCreationRequest data) {
        return product.checkConstraints(
                data.getLoan_term(),
                data.calcPrincipalAmount(),
                data.getInterest(),
                data.getOrigination_amount()
        );
    }
}
