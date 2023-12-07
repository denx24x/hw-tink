package com.academy.fintech.scoring.core.origination.client.grpc;

import com.academy.fintech.scoring.OriginationServiceGrpc;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OriginationGrpcClient {

    private final OriginationServiceGrpc.OriginationServiceBlockingStub stub;

    public OriginationGrpcClient(OriginationGrpcClientProperty property) {
        Channel channel = ManagedChannelBuilder.forAddress(property.host(), property.port()).usePlaintext().build();
        this.stub = OriginationServiceGrpc.newBlockingStub(channel);
    }


}
