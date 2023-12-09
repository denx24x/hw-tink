package com.academy.fintech.pe.agreement;

import com.academy.fintech.pe.controller.creation.AgreementCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.academy.fintech.pe.Util.nextMonth;

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
                .clientId(request.getClient_id())
                .productCode(request.getProduct_code())
                .productVersion(request.getProduct_version())
                .loanTerm(request.getLoan_term())
                .principalAmount(request.calcPrincipalAmount())
                .interest(request.getInterest())
                .originationAmount(request.getOrigination_amount())
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
        agreement.setDisbursementDate(disbursementDate);
        agreement.setNextPaymentDate(nextMonth(disbursementDate));
        agreementRepository.save(agreement);
    }

    public long findMaxOverdue(long clientId){
        List<Agreement> agreementList = agreementRepository.findByClientIdAndStatus(clientId, AgreementStatus.ACTIVE);
        long result = 0;
        for(Agreement agreement : agreementList){
            Date currentTime = new Date();
            if(agreement.getNextPaymentDate().after(currentTime)){
                continue;
            }
            long diffMillis = Math.abs(agreement.getNextPaymentDate().getTime() - currentTime.getTime());
            long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
            result = Math.max(diffDays, result);
        }
        return result;
    }

    public boolean hasCredit(long clientId){
        return agreementRepository.existsByClientIdAndStatus(clientId, AgreementStatus.ACTIVE);
    }
}
