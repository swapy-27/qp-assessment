package com.quetionpro.grocerystore.dtos;

import com.quetionpro.grocerystore.entitites.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter@Getter
public class OrderResponse {
    private String orderId;
    private String orderDate;
    private Double totalAmount;
    private List<OrderItem> orderItems;

}
