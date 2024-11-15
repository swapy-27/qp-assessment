package com.quetionpro.grocerystore.dtos;

import com.quetionpro.grocerystore.entitites.OrderItem;
import com.quetionpro.grocerystore.entitites.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OrderEvent {

    private List<OrderItem> orderItems;
    private List<Product> orderedProducts;

}
