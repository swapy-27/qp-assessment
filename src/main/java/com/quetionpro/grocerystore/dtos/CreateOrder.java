package com.quetionpro.grocerystore.dtos;

import com.quetionpro.grocerystore.entitites.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class CreateOrder {

    private Integer customerId;
    private List<OrderItemDTO> orderItems;

}
