package com.quetionpro.grocerystore.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
public class UpdateProduct {
    private Integer productId;
    private String productName;
    private Double productPrice;
    private String description;
    private Integer inventoryLevel;
}
