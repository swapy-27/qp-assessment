package com.quetionpro.grocerystore.interfaces;

import com.quetionpro.grocerystore.dtos.CreateOrder;
import com.quetionpro.grocerystore.entitites.Order;

public interface OrderService {

    Order createOrder(CreateOrder order);


}
