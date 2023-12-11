package com.academy.fintech.api.core.origination.client.grpc;

import com.academy.fintech.api.core.origination.client.DuplicateApplicationException;
import com.academy.fintech.application.ApplicationServiceGrpc.ApplicationServiceBlockingStub;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OriginationGrpcClient {

    private final ApplicationServiceBlockingStub stub;

    public OriginationGrpcClient(OriginationGrpcClientProperty property) {
        Channel channel = ManagedChannelBuilder.forAddress(property.host(), property.port()).usePlaintext().build();
        this.stub = ApplicationServiceGrpc.newBlockingStub(channel);
    }

    public ApplicationResponse createApplication(ApplicationRequest applicationRequest) throws DuplicateApplicationException {
        try {
            return stub.create(applicationRequest);
        } catch (StatusRuntimeException e) {
            if (e.getStatus().equals(Status.ALREADY_EXISTS)) {
                String id = e.getTrailers().get(Metadata.Key.of("applicationId", Metadata.ASCII_STRING_MARSHALLER));
                throw new DuplicateApplicationException(Integer.parseInt(id));
            }
            log.error("Got error from Origination by request: {}", applicationRequest, e);
            throw e;
        }
    }

    public CancelApplicationResponse cancelApplication(CancelApplicationRequest cancelApplicationRequest) {
        try {
            return stub.cancel(cancelApplicationRequest);
        } catch (StatusRuntimeException e) {
            throw e;
        }
    }

}
