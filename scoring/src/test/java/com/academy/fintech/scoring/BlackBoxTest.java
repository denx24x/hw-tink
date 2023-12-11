package com.academy.fintech.scoring;

import com.academy.fintech.scoring.containers.AppContainer;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

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
        assertEquals(result, "{\"status\":\"UP\"}");
    }

    @Test
    void TestScoringFull() {
        MockServerClient mockServerClient = new MockServerClient(AppContainer.mockServer.getHost(), AppContainer.mockServer.getServerPort());

        mockServerClient
                .when(request().withPath("/hasCredit").withQueryStringParameter("client_id", "1"))
                .respond(response().withBody("false").withContentType(MediaType.APPLICATION_JSON));

        mockServerClient
                .when(request().withPath("/calculateSchedule")
                        .withQueryStringParameter("principal_amount", "50000")
                        .withQueryStringParameter("loan_term", "10")
                        .withQueryStringParameter("interest", "8.5")
                        .withQueryStringParameter("initial_date", "(.*)"))
                .respond(response().withBody("[ {\"id\": \"1\",\n" +
                                "        \"payment_date\": \"2012-04-23T18:25:43.511Z\",\n" +
                                "        \"period_payment\": \"1000\",\n" +
                                "        \"interest_payment\": \"0\",\n" +
                                "        \"principal_payment\": \"0\",\n" +
                                "        \"status\": \"NEW\",\n" +
                                "        \"period_number\": \"1\"}]")
                        .withContentType(MediaType.APPLICATION_JSON));


        Channel channel = ManagedChannelBuilder.forAddress("localhost", Containers.appContainer.getGrpcPort()).usePlaintext().build();
        ScoringServiceGrpc.ScoringServiceBlockingStub stub = ScoringServiceGrpc.newBlockingStub(channel);
        ScoringResponse scoringResponse = stub.requestScoring(
                ScoringRequest.newBuilder()
                        .setOriginationAmount("0")
                        .setClientId(1)
                        .setSalary("100000.0")
                        .setDisbursementAmount("50000")
                        .setInterest("8.5")
                        .setLoanTerm(10)
                        .build()
        );
        Assertions.assertEquals("2", scoringResponse.getResult());
    }

    @Test
    void TestScoringNegative() {
        MockServerClient mockServerClient = new MockServerClient(AppContainer.mockServer.getHost(), AppContainer.mockServer.getServerPort());

        mockServerClient
                .when(request().withPath("/hasCredit").withQueryStringParameter("client_id", "2"))
                .respond(response().withBody("true").withContentType(MediaType.APPLICATION_JSON));

        mockServerClient
                .when(request().withPath("/getMaxOverdue").withQueryStringParameter("client_id", "2"))
                .respond(response().withBody("10").withContentType(MediaType.APPLICATION_JSON));

        mockServerClient
                .when(request().withPath("/calculateSchedule")
                        .withQueryStringParameter("principal_amount", "5000")
                        .withQueryStringParameter("loan_term", "10")
                        .withQueryStringParameter("interest", "8.5")
                        .withQueryStringParameter("initial_date", "(.*)"))
                .respond(response().withBody("[ {\"id\": \"1\",\n" +
                                "        \"payment_date\": \"2012-04-23T18:25:43.511Z\",\n" +
                                "        \"period_payment\": \"1000\",\n" +
                                "        \"interest_payment\": \"0\",\n" +
                                "        \"principal_payment\": \"0\",\n" +
                                "        \"status\": \"NEW\",\n" +
                                "        \"period_number\": \"1\"}]")
                        .withContentType(MediaType.APPLICATION_JSON));


        Channel channel = ManagedChannelBuilder.forAddress("localhost", Containers.appContainer.getGrpcPort()).usePlaintext().build();
        ScoringServiceGrpc.ScoringServiceBlockingStub stub = ScoringServiceGrpc.newBlockingStub(channel);
        ScoringResponse scoringResponse = stub.requestScoring(
                ScoringRequest.newBuilder()
                        .setOriginationAmount("0")
                        .setClientId(2)
                        .setSalary("100.0")
                        .setDisbursementAmount("5000")
                        .setInterest("8.5")
                        .setLoanTerm(10)
                        .build()
        );
        Assertions.assertEquals("-1", scoringResponse.getResult());
    }
}
