package com.academy.fintech.origination.core.scoring.client.grpc;

import com.academy.fintech.application.ApplicationServiceGrpc;
import com.academy.fintech.origination.core.scoring.client.ScoringClientService;
import com.academy.fintech.scoring.ScoringServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ScoringGrpcClient {

    private final ScoringServiceGrpc.ScoringServiceBlockingStub stub;

    public ScoringGrpcClient(ScoringGrpcClientConfiguration property) {
        Channel channel = ManagedChannelBuilder.forAddress(property.host(), property.port()).usePlaintext().build();
        this.stub = ScoringServiceGrpc.newBlockingStub(channel);
    }

}
