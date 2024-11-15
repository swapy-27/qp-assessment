package com.quetionpro.grocerystore.interfaces;

import com.quetionpro.grocerystore.dtos.OrderEvent;

public interface Observer {

    void update(OrderEvent orderEvent);
}
