package com.academy.fintech.pe.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Provides composite foreign and primary key (code, version)
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductID implements Serializable {
        private String code;
        private String version;
}
