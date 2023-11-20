package com.academy.fintech.pe.controller.creation;

import com.academy.fintech.pe.agreement.AgreementService;
import com.academy.fintech.pe.product.Product;
import com.academy.fintech.pe.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AgreementCreationController {
    @Autowired
    private AgreementService agreementService;
    @Autowired
    private ProductService productService;
    @PostMapping("/createAgreement")
    @ResponseBody
    public AgreementCreationResponse createAgreement(@RequestBody AgreementCreationRequest request){
        Optional<Product> tempProduct = this.productService.getProduct(request.product_code, request.product_version);
        if(tempProduct.isEmpty()){
            return new AgreementCreationResponse("No such product");
        }
        Product product = tempProduct.get();

        if(!this.productService.checkProductConstraints(product, request)){
            return new AgreementCreationResponse("Product constraints failed");
        }
        Integer id = this.agreementService.createAgreement(request);
        return new AgreementCreationResponse(id);
    }
}
