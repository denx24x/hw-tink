package com.academy.fintech.scoring;

import com.academy.fintech.scoring.containers.AppContainer;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

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
    void TestPositive(){
        MockServerClient mockServerClient = new MockServerClient(AppContainer.mockServer.getHost(), AppContainer.mockServer.getServerPort());

        mockServerClient
                .when(request().withPath("/hasCredit").withQueryStringParameter("clientId", "1"))
                .respond(response().withBody("{false}"));

        mockServerClient
                .when(request().withPath("/calculateSchedule")
                        .withQueryStringParameter("loan_term", "10")
                        .withQueryStringParameter("principal_amount", "*50000*")
                        .withQueryStringParameter("interest", "8.5")
                        .withQueryStringParameter("initial_date", "*"))
                .respond(response().withBody("[ {\"id\": \"1\",\n" +
                        "        \"payment_date\": \"2012-04-23T18:25:43.511Z\",\n" +
                        "        \"period_payment\": \"1000\",\n" +
                        "        \"interest_payment\": \"0\",\n" +
                        "        \"principal_payment\": \"0\",\n" +
                        "        \"status\": \"NEW\",\n" +
                        "        \"period_number\": \"1\"}]"));


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
        Assertions.assertEquals("3",  scoringResponse.getResult());


    }
}
