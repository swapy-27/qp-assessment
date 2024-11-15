package com.quetionpro.grocerystore.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CreateProduct {
    private String productName;

    private String productPrice;

    private Integer inventoryLevel;

    private String description;
}
