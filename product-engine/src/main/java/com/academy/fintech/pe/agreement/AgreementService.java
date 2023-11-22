package com.academy.fintech.pe.agreement;

import com.academy.fintech.pe.controller.creation.AgreementCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AgreementService {
    @Autowired
    private AgreementRepository agreementRepository;

    /**
     * Creates agreement using provided data.
     * Product with such (code, version) should exist in database.
     * @param request data provided in request
     */
    public Agreement createAgreement(AgreementCreationRequest request){
        Agreement agreement = Agreement.builder()
                .client_id(request.getClient_id())
                .product_code(request.getProduct_code())
                .product_version(request.getProduct_version())
                .loan_term(request.getLoan_term())
                .principal_amount(request.calcPrincipalAmount())
                .interest(request.getInterest())
                .origination_amount(request.getOrigination_amount())
                .status(AgreementStatus.NEW)
                .build();
        return agreementRepository.save(agreement);
    }

    public Optional<Agreement> getAgreement(int id){
        return agreementRepository.findById(id);
    }

    /**
     * Changes {@code status} and {@code disbursement_date} of agreement with provided {@code id}
     * {@code status} changes to {@code ACTIVE}
     * {@code disbursement_date} changes to provided date
     * Agreement with {@code id} should exist in database.
     */
    public void activateAgreement(Integer id, Date disbursementDate){
        Optional<Agreement> tempAgreement = agreementRepository.findById(id);
        if(tempAgreement.isEmpty()){
            throw new RuntimeException("No such agreement");
        }
        Agreement agreement = tempAgreement.get();
        agreement.setStatus(AgreementStatus.ACTIVE);
        agreement.setDisbursement_date(disbursementDate);

        agreementRepository.save(agreement);
    }
}
