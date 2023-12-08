package com.academy.fintech.origination.core.scoring.client;

import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.core.scoring.client.grpc.ScoringGrpcClient;
import com.academy.fintech.scoring.ScoringRequest;
import com.academy.fintech.scoring.ScoringResponse;
import com.academy.fintech.scoring.ScoringServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class ScoringClientService {
    @Autowired
    private ScoringGrpcClient scoringGrpcClient;

    public BigDecimal requestScoring(Application scoringRequest){
        return BigDecimal.ZERO;
    }
}
