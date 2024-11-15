package com.quetionpro.grocerystore.controller;

import com.quetionpro.grocerystore.dtos.CreateOrder;
import com.quetionpro.grocerystore.dtos.OrderResponse;
import com.quetionpro.grocerystore.entitites.Order;
import com.quetionpro.grocerystore.entitites.Product;
import com.quetionpro.grocerystore.interfaces.OrderService;
import com.quetionpro.grocerystore.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grocery-store")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-order")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrder order) {

        Order newOrder = orderService.createOrder(order);

        OrderResponse response = new OrderResponse();
        response.setOrderDate(newOrder.getOrderDate().toString());
        response.setOrderItems(newOrder.getOrderItems());
        response.setTotalAmount(newOrder.getTotalPrice());


        return new ResponseEntity<OrderResponse>(response, HttpStatus.OK);


    }

}
