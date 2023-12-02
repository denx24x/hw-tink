package com.academy.fintech.origination;

import com.academy.fintech.application.*;
import com.academy.fintech.origination.core.application.DuplicateApplicationException;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Testcontainers
public class BlackBoxTest {

    @BeforeAll
    public static void setUpAll() {
        Containers.appContainer.start();
    }

    /**
     * Simple test that should always success
     */
    @Test
    void testReadiness() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + Containers.appContainer.getHttpPort() + "/actuator/health/readiness"))
                .GET().build();
        String result = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        Assertions.assertEquals(result, "{\"status\":\"UP\"}");
    }

    @Test
    void testApplication() {
        Channel channel = ManagedChannelBuilder.forAddress("localhost", Containers.appContainer.getGrpcPort()).usePlaintext().build();
        ApplicationServiceGrpc.ApplicationServiceBlockingStub stub = ApplicationServiceGrpc.newBlockingStub(channel);
        ApplicationResponse applicationResponse = stub.create(ApplicationRequest.newBuilder()
                .setEmail("1233@123.ru")
                .setFirstName("Andrew")
                .setLastName("Borisov")
                .setDisbursementAmount("123.123")
                .setSalary("123")
                .build());
        Assertions.assertTrue(applicationResponse.getApplicationId() >= 0);
    }

    @Test
    void testApplicationDuplicate() {
        Channel channel = ManagedChannelBuilder.forAddress("localhost", Containers.appContainer.getGrpcPort()).usePlaintext().build();
        ApplicationServiceGrpc.ApplicationServiceBlockingStub stub = ApplicationServiceGrpc.newBlockingStub(channel);
        ApplicationResponse applicationResponse = stub.create(ApplicationRequest.newBuilder()
                .setEmail("123@123.ru")
                .setFirstName("Andrew")
                .setLastName("Borisov")
                .setDisbursementAmount("123.123")
                .setSalary("123")
                .build());
        DuplicateApplicationException exception = Assertions.assertThrows(DuplicateApplicationException.class,
                () -> {
                    stub.create(ApplicationRequest.newBuilder()
                            .setEmail("123@123.ru")
                            .setFirstName("Andrew")
                            .setLastName("Borisov")
                            .setDisbursementAmount("123.123")
                            .setSalary("123")
                            .build());
                });
    }

    @Test
    void testCancel() {
        Channel channel = ManagedChannelBuilder.forAddress("localhost", Containers.appContainer.getGrpcPort()).usePlaintext().build();
        ApplicationServiceGrpc.ApplicationServiceBlockingStub stub = ApplicationServiceGrpc.newBlockingStub(channel);
        ApplicationResponse applicationResponse = stub.create(ApplicationRequest.newBuilder()
                .setEmail("1235@123.ru")
                .setFirstName("Andrew")
                .setLastName("Borisov")
                .setDisbursementAmount("123.123")
                .setSalary("123")
                .build());
        CancelApplicationResponse cancelApplicationResponse = stub.cancel(
                CancelApplicationRequest.newBuilder()
                        .setApplicationId(applicationResponse.getApplicationId()).build()
        );
        Assertions.assertTrue(cancelApplicationResponse.getSuccess());
    }
}