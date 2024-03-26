package com.academy.fintech.origination;

import com.academy.fintech.origination.core.service.application.ApplicationService;
import com.academy.fintech.origination.core.db.application.Application;
import com.academy.fintech.origination.core.db.application.ApplicationRepository;
import com.academy.fintech.origination.core.db.application.ApplicationStatus;
import com.academy.fintech.origination.core.db.client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
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
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
public class ApplicationServiceTest {
    @MockBean
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;

    @Test
    public void checkDuplicateEmptyTest() {
        Mockito.when(applicationRepository.findByClientIdAndRequestedDisbursementAmountAndStatus(Mockito.anyInt(), Mockito.any(BigDecimal.class), Mockito.any(ApplicationStatus.class))).thenReturn(List.of());
        Date firstTime = new Date();

        Application first_application = Application.builder()
                .id(1)
                .clientId(1)
                .status(ApplicationStatus.NEW)
                .requestedDisbursementAmount(BigDecimal.ONE)
                .creationTime(firstTime)
                .build();

        Client client = Client.builder()
                .salary(BigDecimal.ONE)
                .email("123")
                .lastName("123")
                .firstName("123")
                .id(1)
                .build();

        Optional<Integer> result = applicationService.checkDuplicate(first_application, client);
        Assertions.assertTrue(result.isEmpty());

    }

    @Test
    public void checkSingleDuplicateTest() {
        Date firstTime = new Date();
        Date secondTime = new Date(firstTime.getTime() - TimeUnit.SECONDS.toMillis(10));

        Application first_application = Application.builder()
                .id(1)
                .clientId(1)
                .status(ApplicationStatus.NEW)
                .requestedDisbursementAmount(BigDecimal.ONE)
                .creationTime(secondTime)
                .build();
        Mockito.when(applicationRepository.findByClientIdAndRequestedDisbursementAmountAndStatus(Mockito.anyInt(), Mockito.any(BigDecimal.class), Mockito.any(ApplicationStatus.class))).thenReturn(List.of(first_application));
        Application second_application = Application.builder()
                .id(2)
                .clientId(1)
                .status(ApplicationStatus.NEW)
                .requestedDisbursementAmount(BigDecimal.ONE)
                .creationTime(firstTime)
                .build();

        Client client = Client.builder()
                .salary(BigDecimal.ONE)
                .email("123")
                .lastName("123")
                .firstName("123")
                .id(1)
                .build();

        Optional<Integer> result = applicationService.checkDuplicate(second_application, client);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(result.get(), 1);
    }


    @TestConfiguration
    static class AgreementServiceTestContextConfiguration {
        @Bean
        public ApplicationService agreementService() {
            return new ApplicationService();
        }
    }
}
