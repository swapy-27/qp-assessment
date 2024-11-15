package com.quetionpro.grocerystore.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AvailableProduct {

    private Integer productId;

    private String productName;

    private String productPrice;
    private String description;

    private Long inventoryLevel;


}
