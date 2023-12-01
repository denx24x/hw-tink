package com.academy.fintech.origination.grpc.application.v1;

import com.academy.fintech.application.ApplicationRequest;
import com.academy.fintech.application.ApplicationResponse;
import com.academy.fintech.application.ApplicationServiceGrpc;
import com.academy.fintech.origination.core.application.ApplicationService;
import com.academy.fintech.origination.core.client.ClientService;
import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.core.db.client.Client;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@GRpcService
public class ApplicationController extends ApplicationServiceGrpc.ApplicationServiceImplBase {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ClientService clientService;

    @Transactional
    @Override
    public void create(ApplicationRequest request, StreamObserver<ApplicationResponse> responseObserver) {
        log.info("Got request: {}", request);

        Client client = clientService.getClient(request);
        Application application = applicationService.createApplication(request, client);

        responseObserver.onNext(
                ApplicationResponse.newBuilder()
                        .setApplicationId(application.getId())
                        .build()
        );
        responseObserver.onCompleted();
    }

}
