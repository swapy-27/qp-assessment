package com.quetionpro.grocerystore.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class InventoryUpdate {
    private Integer productId;
    private Integer inventoryLevel;

}
