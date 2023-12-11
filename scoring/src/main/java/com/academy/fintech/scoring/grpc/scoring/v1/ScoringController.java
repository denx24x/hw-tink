package com.academy.fintech.scoring.grpc.scoring.v1;

import com.academy.fintech.scoring.ScoringRequest;
import com.academy.fintech.scoring.ScoringResponse;
import com.academy.fintech.scoring.ScoringServiceGrpc;
import com.academy.fintech.scoring.core.scoring.ScoringService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@GRpcService
public class ScoringController extends ScoringServiceGrpc.ScoringServiceImplBase {
    @Autowired
    private ScoringService scoringService;

    @Override
    public void requestScoring(ScoringRequest scoringRequest, StreamObserver<ScoringResponse> responseObserver) {

        responseObserver.onNext(ScoringResponse.newBuilder()
                .setResult(scoringService.requestScoring(scoringRequest).toString())
                .build());

        responseObserver.onCompleted();
    }
}
