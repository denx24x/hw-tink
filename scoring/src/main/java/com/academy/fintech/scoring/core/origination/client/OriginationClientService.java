package com.academy.fintech.scoring.core.origination.client;

import com.academy.fintech.scoring.core.origination.client.grpc.OriginationGrpcClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OriginationClientService {

    private final OriginationGrpcClient originationGrpcClient;

}
