package com.academy.fintech.pe.cache_loan;

import com.academy.fintech.pe.Containers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgreementCreationTest {
    @BeforeAll
    public static void setUpAll(){
        Containers.appContainer.start();
    }


}
