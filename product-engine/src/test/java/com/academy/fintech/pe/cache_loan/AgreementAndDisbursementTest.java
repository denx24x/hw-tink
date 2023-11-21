package com.academy.fintech.pe.cache_loan;

import com.academy.fintech.pe.Containers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgreementAndDisbursementTest {
    @BeforeAll
    public static void setUpAll(){
        Containers.appContainer.start();
    }

    @Test
    void testSimple() throws IOException, InterruptedException, JSONException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest createRequest =  HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + Containers.appContainer.getHttpPort() + "/createAgreement"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\n" +
                        "    \"client_id\": 10,\n" +
                        "    \"product_code\": \"CL\",\n" +
                        "    \"product_version\": \"1.0\",\n" +
                        "    \"loan_term\": 5,\n" +
                        "    \"disbursement_amount\": 53000,\n" +
                        "    \"interest\": 10,\n" +
                        "    \"origination_amount\": 3000\n" +
                        "}")).build();
        String result = client.send(createRequest, HttpResponse.BodyHandlers.ofString()).body();
        JSONObject json = new JSONObject(result);
        assertTrue(json.has("success"));
        assertTrue(json.getBoolean("success"));
        assertTrue(json.has("agreementCode"));
        int id = json.getInt("agreementCode");
        JSONObject requestData = new JSONObject();
        requestData.put("agreement_id", id);
        requestData.put("disbursement_date",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().getTime()));
        HttpRequest disbursementRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + Containers.appContainer.getHttpPort() + "/disbursement"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestData.toString()))
                .build();
        String finalResponse = client.send(disbursementRequest, HttpResponse.BodyHandlers.ofString()).body();
        JSONObject finalJson = new JSONObject(finalResponse);
        assertTrue(finalJson.has("success"));
        assertTrue(finalJson.getBoolean("success"));
        assertTrue(finalJson.has("scheduleId"));
        int scheduleId = finalJson.getInt("scheduleId");
    }

}
