package com.academy.fintech.origination.grpc.application.v1;

import com.academy.fintech.application.*;
import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.core.db.client.Client;
import com.academy.fintech.origination.core.service.application.ApplicationService;
import com.academy.fintech.origination.core.service.application.DuplicateApplicationException;
import com.academy.fintech.origination.core.service.client.ClientService;
import io.grpc.Metadata;
import io.grpc.Status;
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
        try {
            Client client = clientService.getClient(request);
            Application application = applicationService.createApplication(request, client);
            responseObserver.onNext(
                    ApplicationResponse.newBuilder()
                            .setApplicationId(application.getId())
                            .build()
            );
            log.info("Response id: {}", application.getId());
            responseObserver.onCompleted();
        } catch (DuplicateApplicationException e) {
            log.info("Duplicate application: {}", e.getDuplicateId());

            Metadata metadata = new Metadata();
            metadata.put(Metadata.Key.of("applicationId", Metadata.ASCII_STRING_MARSHALLER), String.valueOf(e.getDuplicateId()));
            responseObserver.onError(
                    Status.ALREADY_EXISTS.withDescription("Application already exists")
                            .asRuntimeException(metadata)
            );
            log.info("Response id: {}", e.getDuplicateId());
        }


    }

    @Override
    public void cancel(CancelApplicationRequest request, StreamObserver<CancelApplicationResponse> responseObserver) {
        log.info("Got request: {}", request);

        boolean result = applicationService.cancelApplication(request.getApplicationId());
        responseObserver.onNext(
                CancelApplicationResponse.newBuilder()
                        .setSuccess(result)
                        .build()
        );
        responseObserver.onCompleted();
    }
}
