package com.academy.fintech.pe;

import com.academy.fintech.pe.product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for product "CL 1.0"
 */
public class CacheLoanTest {
    private Product cacheLoan = Product.builder()
            .code("CL")
            .version("1.0")
            .loan_term_min(3)
            .loan_term_max(24)
            .interest_min(BigDecimal.valueOf(8))
            .interest_max(BigDecimal.valueOf(15))
            .origination_amount_min(BigDecimal.valueOf(2000))
            .origination_amount_max(BigDecimal.valueOf(10000))
            .principal_amount_min(BigDecimal.valueOf(50000))
            .principal_amount_max(BigDecimal.valueOf(500000))
            .build();

    @Test
    public void testValid() {
        assertTrue(
                cacheLoan.checkConstraints(
                        5,
                        BigDecimal.valueOf(56000),
                        BigDecimal.valueOf(8),
                        BigDecimal.valueOf(3000)
                ));
    }

    @Test
    public void testInvalid() {
        assertFalse(
                cacheLoan.checkConstraints(
                        8,
                        BigDecimal.valueOf(20000),
                        BigDecimal.valueOf(9),
                        BigDecimal.valueOf(4000)
                )
        );
    }
}
