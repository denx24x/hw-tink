package com.academy.fintech.origination.core.scoring.client;

import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.core.db.client.Client;
import com.academy.fintech.origination.core.scoring.client.grpc.ScoringGrpcClient;
import com.academy.fintech.origination.product.ProductProperty;
import com.academy.fintech.scoring.ScoringRequest;
import com.academy.fintech.scoring.ScoringResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class ScoringClientService {
    private ScoringGrpcClient scoringGrpcClient;

    private ProductProperty productProperty;

    public ScoringClientService(ScoringGrpcClient scoringGrpcClient, ProductProperty productProperty){
        this.scoringGrpcClient = scoringGrpcClient;
        this.productProperty = productProperty;
    }

    public BigDecimal requestScoring(Application application, Client client){
        ScoringResponse scoringResponse = scoringGrpcClient.requestScoring(
                ScoringRequest.newBuilder()
                        .setClientId(application.getClientId())
                        .setDisbursementAmount(application.getRequestedDisbursementAmount().toString())
                        .setInterest(productProperty.interest().toString())
                        .setOriginationAmount(productProperty.origination_amount().toString())
                        .setLoanTerm(productProperty.loan_term())
                        .setSalary(client.getSalary().toString()).build()
        );
        return new BigDecimal(scoringResponse.getResult());
    }
}
