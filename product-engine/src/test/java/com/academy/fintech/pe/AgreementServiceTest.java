package com.academy.fintech.pe;

import com.academy.fintech.pe.agreement.Agreement;
import com.academy.fintech.pe.agreement.AgreementRepository;
import com.academy.fintech.pe.agreement.AgreementService;
import com.academy.fintech.pe.controller.creation.AgreementCreationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
public class AgreementServiceTest {
    @MockBean
    private AgreementRepository agreementRepository;

    @Autowired
    private AgreementService agreementService;

    @Test
    public void createAgreementTest() {
        Mockito.when(agreementRepository.save(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        Agreement result = this.agreementService.createAgreement(AgreementCreationRequest.builder()
                .client_id(10)
                .product_code("CL")
                .product_version("1.0")
                .loan_term(5)
                .interest(BigDecimal.valueOf(10))
                .origination_amount(BigDecimal.valueOf(3000))
                .disbursement_amount(BigDecimal.valueOf(50000))
                .build());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getClient_id(), 10);
    }

    @TestConfiguration
    static class AgreementServiceTestContextConfiguration {
        @Bean
        public AgreementService agreementService() {
            return new AgreementService();
        }
    }
}
