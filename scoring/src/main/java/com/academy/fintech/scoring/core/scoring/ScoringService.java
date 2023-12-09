package com.academy.fintech.scoring.core.scoring;

import com.academy.fintech.scoring.ScoringRequest;
import com.academy.fintech.scoring.core.product_engine.client.ProductEngineClientService;
import com.academy.fintech.scoring.core.product_engine.client.rest.ProductEngineRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ScoringService {

    @Autowired
    private ProductEngineClientService productEngineClientService;

    private BigDecimal getSalaryScoring(long clientId, BigDecimal salary){
        return BigDecimal.ZERO;
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
                .add(getSalaryScoring(scoringRequest.getClientId(), new BigDecimal(scoringRequest.getSalary())))
                .add(getOverdueScoring(scoringRequest.getClientId()));
        return scoringResult;
    }
}
