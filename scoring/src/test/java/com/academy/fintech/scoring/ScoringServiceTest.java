package com.academy.fintech.scoring;

import com.academy.fintech.scoring.core.product_engine.client.ProductEngineClientService;
import com.academy.fintech.scoring.core.scoring.ScoringService;
import com.academy.fintech.scoring.public_interface.payment.dto.PaymentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
public class ScoringServiceTest {
    @Autowired
    private ScoringService scoringService;

    @MockBean
    private ProductEngineClientService productEngineClientService;

    @Test
    public void testSalaryScoring(){
        Mockito.when(productEngineClientService.calcPaymentSchedule(
                Mockito.anyInt(),
                Mockito.any(BigDecimal.class),
                Mockito.any(BigDecimal.class)
        )).thenReturn(
                List.of(
                        PaymentDto.builder()
                            .id(1)
                            .interest_payment(BigDecimal.ZERO)
                            .payment_date(new Date())
                            .period_number(1)
                            .status("NEW")
                            .period_payment(BigDecimal.valueOf(500))
                            .principal_payment(BigDecimal.ZERO).build(),
                        PaymentDto.builder()
                                .id(2)
                                .interest_payment(BigDecimal.ZERO)
                                .payment_date(new Date())
                                .period_number(2)
                                .status("NEW")
                                .period_payment(BigDecimal.valueOf(1000))
                                .principal_payment(BigDecimal.ZERO).build()
                )
        );

        Mockito.when(productEngineClientService.hasCredit(Mockito.anyInt())).thenReturn(false);

        BigDecimal result = scoringService.requestScoring(ScoringRequest.newBuilder()
                .setSalary("3100")
                .setLoanTerm(10)
                .setInterest("8.5")
                .setDisbursementAmount("5000")
                .setOriginationAmount("500")
                .setClientId(1)
                .build());
        Assertions.assertEquals(BigDecimal.valueOf(2), result);

    }

    @Test
    public void testOverdueScoring(){
        Mockito.when(productEngineClientService.calcPaymentSchedule(
                Mockito.anyInt(),
                Mockito.any(BigDecimal.class),
                Mockito.any(BigDecimal.class)
        )).thenReturn(
                List.of(
                        PaymentDto.builder()
                                .id(2)
                                .interest_payment(BigDecimal.ZERO)
                                .payment_date(new Date())
                                .period_number(2)
                                .status("NEW")
                                .period_payment(BigDecimal.valueOf(2000))
                                .principal_payment(BigDecimal.ZERO).build()
                )
        );

        Mockito.when(productEngineClientService.hasCredit(Mockito.anyLong())).thenReturn(true);
        Mockito.when(productEngineClientService.getMaxOverdue(Mockito.anyLong())).thenReturn(8L);

        BigDecimal result = scoringService.requestScoring(ScoringRequest.newBuilder()
                .setSalary("3100")
                .setLoanTerm(10)
                .setInterest("8.5")
                .setDisbursementAmount("5000")
                .setOriginationAmount("500")
                .setClientId(1)
                .build());
        Assertions.assertEquals(BigDecimal.valueOf(-1), result);

    }

    @TestConfiguration
    static class AgreementServiceTestContextConfiguration {
        @Bean
        public ScoringService scoringService() {
                return new ScoringService();
            }
    }
}
