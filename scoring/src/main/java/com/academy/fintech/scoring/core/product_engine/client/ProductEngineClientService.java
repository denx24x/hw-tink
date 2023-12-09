package com.academy.fintech.scoring.core.product_engine.client;

import com.academy.fintech.scoring.core.product_engine.client.rest.ProductEngineRestClient;
import com.academy.fintech.scoring.public_interface.payment.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductEngineClientService {
    @Autowired
    private ProductEngineRestClient productEngineRestClient;

    public boolean hasCredit(long clientId){
        return productEngineRestClient.hasCredit(clientId);
    }

    public long getMaxOverdue(long clientId){
        return productEngineRestClient.getMaxOverdue(clientId);
    }

    public List<PaymentDto> calcPaymentSchedule(int loanTerm,
                                                BigDecimal principalAmount,
                                                BigDecimal interest){
        return productEngineRestClient.calcPaymentSchedule(loanTerm, principalAmount, interest);
    }
}
