package com.quetionpro.grocerystore.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class OrderItemDTO {
    private Integer groceryItemId;
    private Integer quantity;
}
