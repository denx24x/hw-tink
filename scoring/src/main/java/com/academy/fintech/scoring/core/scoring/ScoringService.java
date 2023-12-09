package com.academy.fintech.scoring.core.scoring;

import com.academy.fintech.scoring.ScoringRequest;
import com.academy.fintech.scoring.core.product_engine.client.ProductEngineClientService;
import com.academy.fintech.scoring.core.product_engine.client.rest.ProductEngineRestClient;
import com.academy.fintech.scoring.public_interface.payment.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ScoringService {

    @Autowired
    private ProductEngineClientService productEngineClientService;

    private BigDecimal getSalaryScoring(BigDecimal salary, int loanTerm, BigDecimal originationAmount, BigDecimal interest, BigDecimal disbursementAmount){
        List<PaymentDto> paymentDtoList = productEngineClientService.calcPaymentSchedule(
                loanTerm,
                originationAmount.add(disbursementAmount),
                interest
        );
        for(PaymentDto paymentDto : paymentDtoList){
            if(paymentDto.period_payment().compareTo(
                    salary.divide(BigDecimal.valueOf(3), 10, RoundingMode.HALF_EVEN)
            ) > 0){
                return BigDecimal.ZERO;
            }
        }
        return BigDecimal.ONE;
    }

    private BigDecimal getOverdueScoring(long clientId){
        if(productEngineClientService.hasCredit(clientId)){
            long maxOverdue = productEngineClientService.getMaxOverdue(clientId);
            if(maxOverdue > 7){
                return BigDecimal.ONE.negate();
            }else if(maxOverdue > 0){
                return BigDecimal.ZERO;
            }else{
                return BigDecimal.ONE;
            }
        }else{
            return BigDecimal.ONE;
        }
    }

    public BigDecimal requestScoring(ScoringRequest scoringRequest){
        BigDecimal scoringResult = BigDecimal.ZERO;
        scoringResult
                .add(getSalaryScoring(new BigDecimal(scoringRequest.getSalary()), scoringRequest.getLoanTerm(), new BigDecimal(scoringRequest.getOriginationAmount()), new BigDecimal(scoringRequest.getInterest()), new BigDecimal(scoringRequest.getDisbursementAmount())))
                .add(getOverdueScoring(scoringRequest.getClientId()));
        return scoringResult;
    }
}
