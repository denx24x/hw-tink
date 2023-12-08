package com.academy.fintech.scoring.grpc.scoring.v1;

import com.academy.fintech.scoring.ScoringRequest;
import com.academy.fintech.scoring.ScoringResponse;
import com.academy.fintech.scoring.ScoringServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService
public class ScoringController extends ScoringServiceGrpc.ScoringServiceImplBase {
    @Override
    public void requestScoring(ScoringRequest scoringRequest, StreamObserver<ScoringResponse> responseObserver){

        responseObserver.onNext(ScoringResponse.newBuilder()
                        .setResult("0.0")
                        .build());

        responseObserver.onCompleted();
    }
}
