package com.academy.fintech.pe.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ProductID.class)
@Entity
@Table(name="product")
public class Product {
    @Id
    private String code;
    @Id
    private String version;
    private int loan_term_min;
    private int loan_term_max;
    private BigDecimal principal_amount_min;
    private BigDecimal principal_amount_max;
    private BigDecimal interest_min;
    private BigDecimal interest_max;
    private BigDecimal origination_amount_min;
    private BigDecimal origination_amount_max;

    private boolean between(BigDecimal left, BigDecimal right, BigDecimal target){
        return left.compareTo(target) <= 0 && target.compareTo(right) <= 0;
    }
    public boolean checkConstraints(int loan_term, BigDecimal principal_amount, BigDecimal interest, BigDecimal origination_amount){
        return  (loan_term_min <= loan_term && loan_term <= loan_term_max) &&
                between(principal_amount_min, principal_amount_max, principal_amount) &&
                between(interest_min, interest_max, interest) &&
                between(origination_amount_min, origination_amount_max, origination_amount);
    }

}
