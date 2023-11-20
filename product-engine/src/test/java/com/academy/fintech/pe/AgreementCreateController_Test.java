package com.academy.fintech.pe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.academy.fintech.pe.controller.creation.*;

@RunWith(SpringRunner.class)
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//        classes = Application.class)
@AutoConfigureMockMvc
public class AgreementCreateController_Test {

    @Autowired
    private MockMvc mvc;
    @Test
    public void test1() throws Exception {
        /*
        mvc.perform(post("/createAgreement").contentType(MediaType.APPLICATION_JSON)
                        .content(
                            AgreementCreationRequest.builder().build()
                        ))
                    .andExpect(status().isOk());

         */
    }
}