package com.academy.fintech.scoring.core.scoring;

import com.academy.fintech.scoring.ScoringRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ScoringService {

    private BigDecimal getSalaryScoring(long clientId, BigDecimal salary){
        return BigDecimal.ZERO;
    }

    private BigDecimal getOverdueScoring(long clientId){
        return BigDecimal.ZERO;
    }

    public BigDecimal requestScoring(ScoringRequest scoringRequest){
        BigDecimal scoringResult = BigDecimal.ZERO;
        scoringResult
                .add(getSalaryScoring(scoringRequest.getClientId(), new BigDecimal(scoringRequest.getSalary())))
                .add(getOverdueScoring(scoringRequest.getClientId()));
        return scoringResult;
    }
}
